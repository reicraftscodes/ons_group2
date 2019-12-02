refreshSkillsList();

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

    console.log(payload);

    return fetch(id === null ? '/api/Skills/AddSkill' : '/api/Skills/EditSkill', {
        method: id === null ? 'POST' : 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(payload)
    });
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
    let skillMap = {
        1: '1 - None',
        2: '2 - Beginner',
        3: '3 - Intermediate',
        4: '4 - Advanced',
        5: '5 - Expert',
    };

    fetch("/api/Skills/User").then((response) => {
        let $table = $('#skillstable');

        $table.empty();

       if(response.ok){
           response.text().then((data) => {
               let results = JSON.parse(data);

               for (let i = 0; i < results.length; i++) {
                   const currentSkill = results[i];

                   let $markup = $(`<tr>
                        <td class="pl_tablecell">${currentSkill.title}</td>
                        <td class="desc_tablecell">${currentSkill.description}</td>
                        <td class="confidence_tablecell">
                            ${skillMap[currentSkill.confidence]}
                        </td>
                        <td>${!currentSkill.category ? "" : currentSkill.category.name}</td>
                        <td>
                            <input id="idField" type="hidden" value="${currentSkill.id}">
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
}