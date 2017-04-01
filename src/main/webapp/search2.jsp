<html>
<head>
    <script type="text/javascript" th:src="@{/js/redline/apiRequest.js}" />
    <script type="text/javascript" th:src="@{/js/redline/vkbeautify.0.98.01.beta.js}" />
    <script type="text/javascript" th:src="@{/js/jquery.js}" />
    <script type="text/javascript" th:src="@{/js/jquery-ui.min.js}" />
</head>
<body>
<div class="container" align="center" style="margin-top: 50px;">
    <div class="accordion" id="accordion-api">
        <a th:href="@{/admin}">Back to admin page</a>
        <p th:text="${hello}" />
        <div class="accordion-group">
            <div id="radius" >
                <form method="GET" action="" onsubmit="makeApiRequest($(this)); return false;">
                    <div>
                        <p>
                            Find all leagues within a given radius of a zip code.
                        <div style="display: none;">
                            <code class="endpoint">https://zipcodedistanceapi.redline13.com/rest/&lt;api_key&gt;/radius.&lt;format&gt;/&lt;zip_code&gt;/&lt;distance&gt;/&lt;units&gt;?minimal</code>.
                        </div>
                        </p>
                        <div class="row" style="display: none;">
                            <div class="span6">
                                <label>API Key</label>
                                <input class="input-block-level" type="text" name="api_key" value="js-GcUhqNYJbu1CeWQdDY3KRxQvJ5kox1tednwDbummYoZdlPqYOuIG1zcLEwacxDqK" required="required" />
                            </div>
                        </div>
                        <div class="row">
                            <div  style="display: none;">
                                <label>Format</label>
                                <select class="input-mini" name="format" required="required">
                                    <option value="json" selected="selected">json</option>
                                    <option value="xml">xml</option>
                                    <option value="csv">csv</option>
                                </select>
                            </div>

                            <div>
                                <label>Zip Code</label>
                                <input class="input-mini" type="text" name="zip_code" placeholder="Zip Code" required="required" />
                            </div>
                            <div>
                                <label>Distance</label>
                                <input class="input-mini" type="number" min="0" name="distance" placeholder="Distance" required="required" />
                            </div>
                            <div style="display: none;">
                                <label>Units</label>
                                <select class="input-mini" name="units" required="required">
                                    <option value="km">km</option>
                                    <option value="mile" selected="selected">mile</option>
                                </select>
                            </div>
                            <div class="span2 offset1">
                                <label>&nbsp;</label>
                                <button class="btn" type="submit">Make Request</button>
                                <p th:text="${hello}" />
                            </div>
                        </div>
                        <div id="locationList" th:unless="${#lists.isEmpty(leagues)}">
                            <label for="foundLeagues">Leagues</label>
                            <dl id="foundLeagues" th:each="league : ${leagues}">
                                <dt class="bookHeadline">
                                    <span th:text="${league.name}">Name</span>
                                    &nbsp;&nbsp;&nbsp;
                                    <a th:href="@{/search/} + ${league.id} "><span>Go to site</span></a>
                                </dt>
                            </dl>
                            <!--<select id="foundLeagues">-->
                            <!--<option value="0" text="Select a location" ></option>-->
                            <!--<option th:each="league : ${locations}" th:value="${league.id}" th:text="#{${league.name}}">locations</option>-->
                            <!--</select>-->
                        </div>
                        <div th:if="${#lists.isEmpty(locations)}">
                            <p>No leagues were found.</p>
                        </div>
                        <div class="request-url" style="display: none; margin: 5px 0;">
                            <span class="label">Request URL</span>
                            <code></code>
                        </div>
                        <pre class="response" style="display: none;"></pre>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>