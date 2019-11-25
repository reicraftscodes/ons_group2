refreshSkillsList();
getCategories();

function getCategories() {
    return $.get({
        url: "/api/categories/"
    })
}


function addSkill() {
    let xhr = new XMLHttpRequest();

    let title = document.querySelector("#skillName");
    let description = document.querySelector("#description");
    let confidence = document.querySelector("#confidence");

    let payload = {
        "title": title.value,
        "description": description.value,
        "confidence": confidence.value
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
    fetch("/api/Skills/").then((response) => {
        let $table = $('.primary tbody');

        $table.empty();

       if(response.ok){
           response.text().then((data) => {
               let results = JSON.parse(data);

               for (let i = 0; i < results.length; i++) {

                   let markup = `<tr>
                            <td>${results[i].title}</td>
                            <td>${results[i].description}</td>
                            <td>${results[i].confidence}</td>
                            <td><button onclick="removeSkill('${results[i].id}')" class="error">-</button></td>
                        </tr>`;

                   $table.append(markup);
               }

               let newSkillMarkup = `<tr>
                            <td><input type="text" id="skillName" placeholder="Name"></td>
                            <td><input type="text" id="description" placeholder="Tell us more"></td>
                            <td><input type="number" id="confidence" placeholder="Confidence (1 - 5)"></td>
                            <td><select id="categorySelect"></select></td>
                            <td><button onclick="addSkill()" class="warning">+</button></td>
                        </tr>`;

               $table.append(newSkillMarkup);

               getCategories().then((data) => {

                   let $categorySelect = $('#categorySelect');

                   for (let i = 0; i < data.length; i++) {
                       const currentCategory = data[i];

                       $categorySelect.append(new Option(currentCategory.name, currentCategory.id));
                   }
               });
           })
       }


    });
}