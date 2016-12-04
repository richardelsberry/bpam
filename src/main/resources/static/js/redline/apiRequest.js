/** Make an API Request */
function makeApiRequest(form)
{
    var button = form.find("button.submit");
    var endpoint = form.find("code.endpoint").text();
    var inputs = form.find(":input");
    var errorElem = $("#apiError");
    var responseElem = form.find("pre.response");
    var requestUrlElem = form.find("div.request-url");

    // Check for endpoint
    if (!endpoint)
    {
        errorElem.find("p.error").text("Endpoint not set.");
        errorElem.modal({});
    }
    else
    {
        // Hide response
        responseElem.hide();

        // Build url
        var url = endpoint;
        var patt = /<([^>]+)>/g;
        var match;
        var format = null;
        while ((match = patt.exec(endpoint)))
        {
            url = url.replace("<"+match[1]+">", inputs.filter("[name='"+match[1]+"']").val());
            if (match[1] == 'format')
                format = inputs.filter("[name='"+match[1]+"']").val();
        }

        // Output url
        requestUrlElem.find("code").text(url);
        requestUrlElem.show();

        // Send AJAX Request
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'text'
        }).done(function(rtn) {
            // Beautify
            if (format == "json")
            {
                try {
                    rtn = vkbeautify.json(rtn);
                } catch (e) {}
            }
            else if (format == "xml")
            {
                try {
                    rtn = vkbeautify.xml(rtn);
                } catch (e) {}
            }
            var jsonData = JSON.parse(rtn);
            //for (var i = 0; i < jsonData.zip_codes.length; i++) {
            //    var counter = jsonData.zip_codes[i];
            //    console.log(zip_codes);
            //}
            var zipCodes = jsonData.zip_codes
            getZipCodeList(zipCodes)
            // Show response
            responseElem.text(rtn).show();
        }).fail(function(rtn) {
            errorElem.find("p.error").text("API Request Failed.");
            errorElem.modal({});

            // Beautify
            if (format == "json")
            {
                try {
                    rtn = vkbeautify.json(rtn.responseText);
                } catch (e) {}
            }
            else if (format == "xml")
            {
                try {
                    rtn = vkbeautify.xml(rtn.responseText);
                } catch (e) {}
            }

            // Show response
            responseElem.text(rtn).show();
        });
    }

    function getZipCodeList(jsonList){
        $.ajax({
            type: "GET",
            url: "/bpam/home/list?jsonList="+jsonList,
            dataType: 'text'
        }).done(function(rtn) {
            // Beautify

            var response = rtn
            //if (format == "json")
            //{
            //    try {
            //        rtn = vkbeautify.json(rtn);
            //    } catch (e) {}
            //}
            //else if (format == "xml")
            //{
            //    try {
            //        rtn = vkbeautify.xml(rtn);
            //    } catch (e) {}
            //}

            // Show response
            //responseElem.text(rtn).show();
        }).fail(function(rtn) {
            errorElem.find("p.error").text("API Request Failed.");
            errorElem.modal({});



            // Show response
            //responseElem.text(rtn).show();
        })
    }
}