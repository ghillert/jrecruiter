        Dear ${user.firstName} ${user.lastName},

        <p>Thank you for registering your user account!</p>

        <p>Please follow the following link to complete the registration process:</p>

        <br/>
            <b><a href="${accountValidationUrl}?key=${registrationCode}">${accountValidationUrl}?key=${registrationCode}</a></b>
        <br/>

        <p>Alternatively, you can can visit the following url and enter the verification code <b>${registrationCode}</b> manually:</p>
        <br/>
            <b><a href="${accountValidationUrl}">${accountValidationUrl}</a></b>
        <br/>
        <p>Thank you very much!</p>

        Your friendly jRecruiter Team

