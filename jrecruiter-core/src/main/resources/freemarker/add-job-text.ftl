A new job has been added:

Job ID:               ${jobId?c}

Job Title:            ${jobTitle}

Location:             ${businessLocation}

Business Name:        ${businessName}

Email:        ${businessEmail}



Job Description:
----------------

${description}


Job Restrictions:
----------------

${jobRestrictions}


This job was posted on: ${updateDate?datetime?string.short}


For more information, please visit: ${serverAddress}/job-detail.html?jobId=${jobId?c}



