let selectedId = undefined;

function fillCategoryDropDown(categoryList){
    $dropdownMain = $('#dropdownTable');

    for (let i = 0; i < categoryList.length; i++) {
        const currentCategory = categoryList[i];
        let $markup = $(`<li class="dropdown-submenu">
                <a  class="dropdown-item" tabindex="-1" onclick="selectItem('${currentCategory.id}', '${currentCategory.name}')">${currentCategory.name}</a>                               
            </li>`);

        fillSubCategory(currentCategory, $markup);

        $dropdownMain.append($markup);
    }
}

function fillSubCategory(category, $parent, level = 1){
    if (!category.subCategories){
        return;
    }
    if(category.subCategories.length === 0){return;}
        console.log("level", level, $parent);

        for (let i = 0; i < category.subCategories.length ; i++) {
            const currentSubCategory = category.subCategories[i];

            $ul = $(`<ul class="dropdown-menu"></ul>`);
            $markup = $(`<li class="dropdown-submenu">
                              <a class="dropdown-item" onclick="selectItem('${currentSubCategory.id}', '${currentSubCategory.name}')">${currentSubCategory.name}</a>
                            </li>`);
            $ul.append($markup);
            $parent.append($ul);

            fillSubCategory(currentSubCategory, $markup, level + 1);


    }
}

function selectItem(id, text) {
    selectedId = id;
    $('#dropdownMenu1').text(text);
}