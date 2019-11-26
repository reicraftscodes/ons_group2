function getRootCategories() {
    return $.get({
        url: "/api/categories/topLevel"
    })
}

function createCategory(category) {

}

function removeCategoryById(id) {
    return fetch(`/api/categories/${id}`, {
       method: 'DELETE'
    });
}

function fillSelectWithCategories($categorySelect, categories) {
    $categorySelect.empty();

    for (let i = 0; i < categories.length; i++) {
        const currentCategory = categories[i];
        $categorySelect.append(new Option(currentCategory.name, currentCategory.id));

        fillSelectDomWithSubcategories(currentCategory, $categorySelect);
    }
}

function fillSelectDomWithSubcategories(category, $selectDOM, level = 1) {
    if(!category.subCategories) return;
    if(category.subCategories.length === 0) return;

    for (let i = 0; i < category.subCategories.length; i++) {
        const currentCategory = category.subCategories[i];
        $selectDOM.append(
            new Option('-'.repeat(level) + ' ' + currentCategory.name, currentCategory.id));

        fillSelectDomWithSubcategories(currentCategory, $selectDOM,level + 1);
    }
}