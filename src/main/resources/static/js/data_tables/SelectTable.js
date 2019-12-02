// used to hide all tables but the one selected by the user
function hideAllButOne(optionToShow){
    var options = ["mydatatable","Help Offers"];
    for (var i = 0; i < 2; i++) {
        var idPrefix = "#";
        var tableName = idPrefix.concat(options[i].replace(/ /g,"_").toLowerCase());
        if(options[i] == optionToShow){
            // show selected table
            $(tableName).show();
            console.log("showing ".concat(tableName))
        }else{
            $(tableName).hide();
        }
    }
}


$(document).ready(function(){
    // gets the currently selected table to search
    $("select.selectedSearchCategory").change(function(){
        var selectedCategory = $(this).children("option:selected").val();
        console.log(selectedCategory);
        hideAllButOne(selectedCategory);
    });

});