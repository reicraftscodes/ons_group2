$(document).ready(function(){

    $("select.selectedSearchCategory").change(function(){

        var selectedCategory = $(this).children("option:selected").val();

    });

});