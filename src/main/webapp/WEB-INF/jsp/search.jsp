<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<s:form action="search">

    <div class="required">
       <label for="keyword">Search terms</label>
       <s:textfield name="keyword" id="keyword" size="255" tabindex="1"
                    onfocus="javascript:this.className='selected';"
                    onblur="javascript:this.className='';" />
    </div>
    <div class="submit">
        <s:submit value="Search" method="search"/>
    </div>
</s:form>

