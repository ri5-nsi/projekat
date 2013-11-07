var projectVersionAddObject = '<div id="Project_ProjectVersionChild">'+
        '<a id="RemoveProjectVersion" class="btn btn-small btn-warning"><i class="icon-remove icon-white"></i> Remove</a>'+
        '<input data-val="true" data-val-number="The field ProjectVersionID must be a number." data-val-required="The ProjectVersionID field is required." id="ProjectVersions_x__ProjectVersionID" name="ProjectVersions[x].ProjectVersionID" type="hidden" value="0">'+
        '<div class="control-group"><label class="control-label" for="ProjectVersions_x__Version">Version</label><div class="controls"><input data-val="true" data-val-length="Must be between 1 and 20 charaters." data-val-length-max="20" data-val-length-min="1" data-val-required="This is required" id="ProjectVersions_x__Version" name="ProjectVersions[x].Version" type="text" value=""><span class="field-validation-valid" data-valmsg-for="ProjectVersions[x].Version" data-valmsg-replace="true"></span></div></div>'+
        '<div class="control-group"><label class="control-label" for="ProjectVersions_x__Description">Description</label><div class="controls"><textarea cols="20" id="ProjectVersions_x__Description" name="ProjectVersions[x].Description" rows="2"></textarea><span class="field-validation-valid" data-valmsg-for="ProjectVersions[x].Description" data-valmsg-replace="true"></span></div></div>'+
        '<div class="control-group"><label class="control-label" for="ProjectVersions_x__VersionStatus">Status</label><div class="controls"><select data-val="true" data-val-required="The Status field is required." id="ProjectVersions_x__VersionStatus" name="ProjectVersions[x].VersionStatus"><option selected="selected" value="Unactive">Unactive</option>'+
	    '<option value="Active">Active</option><option value="Obsolete">Obsolete</option></select><span class="field-validation-valid" data-valmsg-for="ProjectVersions[x].VersionStatus" data-valmsg-replace="true"></span></div></div>'+
        '<input data-val="true" data-val-number="The field deleted must be a number." data-val-required="The deleted field is required." id="ProjectVersions_x__deleted" name="ProjectVersions[x].deleted" type="hidden" value="0">'+
    '</div>';
var projectComponentObject = '<div id="Project_ComponentChild">' +
        '<a id="RemoveProjectComponent" class="btn btn-small btn-warning"><i class="icon-remove icon-white"></i> Remove</a>' +
        '<input data-val="true" data-val-number="The field ComponentID must be a number." data-val-required="The ComponentID field is required." id="Components_x__ComponentID" name="Components[x].ComponentID" type="hidden" value="0">' +
        '<div class="control-group"><label class="control-label" for="Components_x__Name">Name</label><div class="controls"><input data-val="true" data-val-length="Must be between 2 and 50 charaters." data-val-length-max="50" data-val-length-min="2" data-val-required="This is required" id="Components_x__Name" name="Components[x].Name" type="text" value=""><span class="field-validation-valid" data-valmsg-for="Components[x].Name" data-valmsg-replace="true"></span></div></div>' +
        '<div class="control-group"><label class="control-label" for="Components_x__Description">Description</label><div class="controls"><textarea cols="20" id="Components_x__Description" name="Components[x].Description" rows="2"></textarea><span class="field-validation-valid" data-valmsg-for="Components[x].Description" data-valmsg-replace="true"></span></div></div>' +
        '<div class="control-group"><label class="control-label" for="Components_x__componentStatus">Status</label><div class="controls"><select data-val="true" data-val-required="The Status field is required." id="Components_x__componentStatus" name="Components[x].componentStatus"><option selected="selected" value="Unactive">Unactive</option>' +
	    '<option value="Active">Active</option><option value="Obsolete">Obsolete</option></select><span class="field-validation-valid" data-valmsg-for="Components[x].componentStatus" data-valmsg-replace="true"></span></div></div>' +
        '<input data-val="true" data-val-number="The field deleted must be a number." data-val-required="The deleted field is required." id="Components_x__deleted" name="Components[x].deleted" type="hidden" value="0">' +
    '</div>';

function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
             .toString(16)
             .substring(1);
};

function guid() {
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
         s4() + '-' + s4() + s4() + s4();
}

$(document).ready(function () {

    $("div#ProjectVersionHolder").find("a#RemoveProjectVersion").click(function () {
        if ($(this).parent().find("input[name$=ProjectVersionID]").val() != "0") {
            $(this).parent().find("input[name$=deleted]").val("1");
            $(this).parent().hide();
        }
    });

    $("a#AddProjectVersion").click(function () {
        var currNumber = $("div#Project_ProjectVersionChild").length;
        var newObj = projectVersionAddObject.replace(/\[x\]/g, "[" + currNumber + "]");
        $("div#ProjectVersionHolder").append(newObj);

        $("div#ProjectVersionHolder").find("div#Project_ProjectVersionChild").last().attr("num", currNumber);

        $("div#ProjectVersionHolder").find("div#Project_ProjectVersionChild").last().find("a#RemoveProjectVersion").click(function () {
            var projectVersionNum = parseInt($(this).parent().attr("num"));
            var currNumber = parseInt($("div#Project_ProjectVersionChild").length);

            for (var i = projectVersionNum + 1; i < currNumber; i++) {
                $("div#ProjectVersionHolder").find("div#Project_ProjectVersionChild[num='" + i + "']").find("input, select, textarea").each(function () {
                    $(this).attr("name", $(this).attr("name").replace("[" + i + "]", "[" + (i - 1) + "]"));
                });

                $("div#ProjectVersionHolder").find("div#Project_ProjectVersionChild[num='" + i + "']").attr("num", i - 1);
            }
            $(this).parent().remove();
        });
    });

    $("div#ProjectComponentHolder").find("a#RemoveProjectComponent").click(function () {
        if ($(this).parent().find("input[name$=ComponentID]").val() != "0") {
            $(this).parent().find("input[name$=deleted]").val("1");
            $(this).parent().hide();
        }
    });

    $("a#AddProjectComponent").click(function () {
        var currNumber = $("div#Project_ComponentChild").length;
        var newObj = projectComponentObject.replace(/\[x\]/g, "[" + currNumber + "]");
        $("div#ProjectComponentHolder").append(newObj);

        $("div#ProjectComponentHolder").find("div#Project_ComponentChild").last().attr("num", currNumber);

        $("div#ProjectComponentHolder").find("div#Project_ComponentChild").last().find("a#RemoveProjectComponent").click(function () {
            var projectVersionNum = parseInt($(this).parent().attr("num"));
            var currNumber = parseInt($("div#Project_ComponentChild").length);

            for (var i = projectVersionNum + 1; i < currNumber; i++) {
                $("div#ProjectComponentHolder").find("div#Project_ComponentChild[num='" + i + "']").find("input, select, textarea").each(function () {
                    $(this).attr("name", $(this).attr("name").replace("[" + i + "]", "[" + (i - 1) + "]"));
                });

                $("div#ProjectComponentHolder").find("div#Project_ComponentChild[num='" + i + "']").attr("num", i - 1);
            }
            $(this).parent().remove();
        });
    });
    
    $("#ProjectForm").validate({
        rules: {
            Name: {
                required: true,
                minlength: 2,
                maxlength: 50
            }
        },
        messages: {
            Name: {
                required: "This field is required.",
                minlength: "Must consist of at least 2 characters.",
                maxlength: "Must consist of at moast 50 characters."
            }
        }
    });

});