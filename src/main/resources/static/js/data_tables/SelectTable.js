// used to hide all tables but the one selected by the user
function hideAllButOne(optionToShow){
    var options = ["mydatatable","Help Offers","Help Requests"]; // Options in the dropdown MAKE SURE TO UPDATE IF ADDING MORE OPTIONS
    for (var i = 0; i < options.length; i++) {
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

    $('#help_offers').hide();
    $('#help_requests').hide();

    // gets the currently selected table to search
    $("select.selectedSearchCategory").change(function(){
        var selectedCategory = $(this).children("option:selected").val();
        hideAllButOne(selectedCategory);
    });

});