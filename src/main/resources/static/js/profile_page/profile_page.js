refreshSkillsList();

function addSkill() {
    let xhr = new XMLHttpRequest();

    let title = document.querySelector("#skillName");
    let description = document.querySelector("#description");
    let confidence = document.querySelector("#confidence");
    let category = document.querySelector("#categorySelect");

    let payload = {
        "title": title.value,
        "description": description.value,
        "confidence": confidence.value,
        "categoryId": category.value
    };

    xhr.open('POST', '/api/Skills/AddSkill');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(payload));

    xhr.onreadystatechange = () => {
        refreshSkillsList();
    }
}

function removeSkill(id) {
    let xhr = new XMLHttpRequest();

    xhr.open('DELETE', `/api/Skills/RemoveSkill/${id}`);
    xhr.send();

    xhr.onreadystatechange = () => {
        refreshSkillsList();
    }
}

function refreshSkillsList() {
    fetch("/api/Skills/User").then((response) => {
        let $table = $('.primary tbody');

        $table.empty();

       if(response.ok){
           response.text().then((data) => {
               let results = JSON.parse(data);

               for (let i = 0; i < results.length; i++) {
                   const currentSkill = results[i];

                   let markup = `<tr>
                            <td>${currentSkill.title}</td>
                            <td>${currentSkill.description}</td>
                            <td>${currentSkill.confidence}</td>
                            <td>${!currentSkill.category ? "" : currentSkill.category.name}</td>
                            <td><button onclick="removeSkill('${currentSkill.id}')" class="error">-</button></td>
                        </tr>`;

                   $table.append(markup);
               }

               let newSkillMarkup = `<tr>
                            <td><input type="text" id="skillName" placeholder="Name"></td>
                            <td><input type="text" id="description" placeholder="Tell us more"></td>
                            <td><input type="number" id="confidence" placeholder="Confidence (1 - 5)"></td>
                            <td><select id="categorySelect" style="width: 115%;"></select></td>
                            <td><button onclick="addSkill()" class="warning">+</button></td>
                        </tr>`;

               $table.append(newSkillMarkup);

               getRootCategories().then((categories) => {
                   fillSelectWithCategories($('#categorySelect'), categories)
               });
           })
       }
    });
}