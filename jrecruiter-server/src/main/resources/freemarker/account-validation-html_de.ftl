<html>
    <body>
        Sehr geehrte(r) ${user.firstName} ${lastName},

        <p>Vielen Dank, dass Sie sich mit userem System registriert haben.</p>

        <p>Um die Registration zu vervollständigen, folgen Sie bitte dem untenstehenden Link:</p>

        <br/>
            <b><a href="${serverAddress}/${registrationCode}">${serverAddress}/${registrationCode}</a></b>
        <br/>
        <p>Alternativ k�nnen Sie auch die untenstehende Webseite besuchen und das Verifizierungskennwort
           <b>${registrationCode}</b>
           dort per Hand eingeben:</p>
        <br/>
            <b><a href="${accountValidationUrl}">${accountValidationUrl}</a></b>
        <br/>
        <p>Nochmals vielen Dank!</p>

        Ihr freundliches  jRecruiter Team
    </body>
</html>

