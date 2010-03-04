<table>
    <tr>
        <td align="center" valign="top">
            <h2>A new job posting:</h2>
            <table>
                <tr><td align="right"><b>Job ID:</b></td>                <td>${jobId?c}</td></tr>
                <tr><td align="right"><b>Job Title:</b></td>             <td>${jobTitle}</td></tr>
                <tr><td align="right"><b>Location:</b></td>              <td>${businessLocation}</td></tr>
                <tr><td align="right"><b>Business Name:</b></td>         <td>${businessName}</td></tr>
                <tr><td align="right"><b>Email:</b></td>                 <td>${businessEmail}</td></tr>
                <tr><td align="right"><b>Job Description:</b></td>       <td>${description}</td></tr>
                <tr><td align="right"><b>Job Restrictions:</b></td>      <td>${jobRestrictions}</td></tr>
                <tr><td align="right"><b>This job was posted on:</b></td><td>${updateDate?datetime?string.short}</td></tr>
            </table>
            <p>For more information, please visit: <a href="${serverAddress}/job-detail.html?jobId=${jobId?c}">${serverAddress}/job-detail.html?jobId=${jobId?c}</a></p>
        </td>
    </tr>
</table>
