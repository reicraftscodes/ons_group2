refreshSkillsList();

<<<<<<< HEAD
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
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(payload));

    xhr.onreadystatechange = () => {
        refreshSkillsList();
    }
=======
function getCategories() {
    return $.get({
        url: "/api/categories/topLevel"
    })
}

function saveSkill(title, description, confidence, category, id = null) {
    let payload = {
        "id": id,
        "title": title,
        "description": description,
        "confidence": confidence,
        "categoryId": category
    };

    return fetch('/api/Skills/AddSkill', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(payload)
    });
>>>>>>> master
}

function removeSkill(id) {
    let xhr = new XMLHttpRequest();

    xhr.open('DELETE', `/api/Skills/RemoveSkill/${id}`);
<<<<<<< HEAD
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
=======
>>>>>>> master
    xhr.send();

    xhr.onreadystatechange = () => {
        refreshSkillsList();
    }
}

function refreshSkillsList() {
<<<<<<< HEAD
    fetch("/api/Skills/").then((response) => {
        let $table = $('.primary tbody');
=======
    let skillMap = {
        1: '1 - None',
        2: '2 - Beginner',
        3: '3 - Intermediate',
        4: '4 - Advance',
        5: '5 - Expert',
    };

    fetch("/api/Skills/User").then((response) => {
        let $table = $('#skillstable');
>>>>>>> master

        $table.empty();

       if(response.ok){
           response.text().then((data) => {
               let results = JSON.parse(data);

               for (let i = 0; i < results.length; i++) {
<<<<<<< HEAD

                   let markup = `<tr>
                            <td>${results[i].title}</td>
                            <td>${results[i].description}</td>
                            <td>${results[i].confidence}</td>
                            <td><button onclick="removeSkill('${results[i].id}')" class="error">-</button></td>
                        </tr>`

                   $table.append(markup);
               }

               let newSkillMarkup = `<tr>
                            <td><input type="text" id="skillName" placeholder="Name"></td>
                            <td><input type="text" id="description" placeholder="Tell us more"></td>
                            <td><input type="number" id="confidence" placeholder="Confidence (1 - 5)"></td>
                            <td><button onclick="addSkill()" class="warning">+</button></td>
                        </tr>`

               $table.append(newSkillMarkup);
           })
       }


    });
=======
                   const currentSkill = results[i];

                   let $markup = $(`<tr>
                        <td class="pl_tablecell">${currentSkill.title}</td>
                        <td class="desc_tablecell">${currentSkill.description}</td>
                        <td class="confidence_tablecell">
                            ${skillMap[currentSkill.confidence]}
                        </td>
                        <td>${!currentSkill.category ? "" : currentSkill.category.name}</td>
                        <td>
                            <a class="add" title="Add"><i
                                    class="fa fa-check"></i></a>
                            <a class="edit" title="Edit"><i
                                    class="fa fa-edit"></i></a>
                            <a onclick="removeSkill('${currentSkill.id}')" class="delete" title="Delete"><i
                                    class="fa fa-trash"></i></a>
                        </td>
                    </tr>`);

                   $table.append($markup);
               }

               getCategories().then((categories) => {
                   let $categorySelect = $('#categorySelect');
                   $categorySelect.empty();

                   for (let i = 0; i < categories.length; i++) {
                       const currentCategory = categories[i];
                       $categorySelect.append(new Option(currentCategory.name, currentCategory.id));

                       displaySubCategories(currentCategory);
                   }
               });
           })
       }
    });
}

function displaySubCategories(category, level = 1) {
    let $categorySelect = $('#categorySelect');

    if(!category.subCategories) return;
    if(category.subCategories.length === 0) return;

    for (let i = 0; i < category.subCategories.length; i++) {
        const currentCategory = category.subCategories[i];
        $categorySelect.append(
            new Option('-'.repeat(level) + ' ' + currentCategory.name, currentCategory.id));

        displaySubCategories(currentCategory, level + 1);
    }
>>>>>>> master
}