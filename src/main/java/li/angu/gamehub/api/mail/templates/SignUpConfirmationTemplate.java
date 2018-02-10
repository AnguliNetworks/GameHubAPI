package li.angu.gamehub.api.mail.templates;

import li.angu.gamehub.api.mail.html.HTMLObject;

import java.util.Map;

/*************************************************************************
 *
 * ANGULI NETWORKS CONFIDENTIAL
 * __________________
 *
 *  [2014] - [2018] Anguli Networks 
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Anguli Networks. The intellectual and 
 * technical concepts contained herein are proprietary to 
 * Anguli Networks and may be covered by German/EU and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Anguli Networks
 *
 * This File belongs to the GameHubAPI from Anguli Networks
 */
public class SignUpConfirmationTemplate implements MailTemplate {

    @Override
    public HTMLObject getHTML(Map<String, String> values) {

        if (values == null) {
            return null;
        }

        HTMLObject html = new HTMLObject("div");
        html.addChildren(new HTMLObject("style", "div#body{font-family:\"Helvetica Neue\",Helvetica,Arial,sans-serif;width:100%;min-height:100vh;margin:0;padding:0;background:#ddd}div.container{max-width:980px;margin:0 auto;padding-top:45px}div.content{background-color:#fff;text-align:center;color:#999;border:1px solid #e6e6e6;padding:10px 30px}img.logo{float:right;height:64px;width:64px}h1.title{margin-top:65px;color:#000;font-weight:400}p{text-align:left}p.info{font-size:13px}p.info b{display:block}a{font-weight:600;text-decoration:none;color:#606061}p.center{text-align:center}a.button{margin:25px 0;width:100%;display:block;padding:11px 8px;border-radius:3px;outline:none;font-size:16px;background-color:#007AFF;color:#fff;cursor:pointer;text-decoration:none}@media only screen and (max-width: 1068px){div.container{max-width:698px}}@media only screen and (max-width: 725px){div.container{width:95%}h1.title{font-weight:500;font-size:18px}}"));

        HTMLObject body = new HTMLObject("div");
        body.setAttribute("id", "body");
        html.addChildren(body);


        HTMLObject container = new HTMLObject("div");

        container.setAttribute("class", "container");

        HTMLObject content = new HTMLObject("div");
        content.setAttribute("class", "content");

        HTMLObject logo = new HTMLObject("img");

        logo.setAttribute("class", "logo");
        logo.setAttribute("src",
                "data:image/jpg;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAJZ0lEQVR4nO2bbWxb1RnHf353EtdO\n" +
                        "YpNDhklpuqSiLUJMnSJFiFRkUUVLVPgARdGEpmh0NEggTZomDa0sUBDVnLRDVDAibUJMisqnForW\n" +
                        "D622qfQD4m0l7ZrQ2wYV5+Xe5c1p7Pj97ENiNwmOfe04IRP5Sf7ge859zvP87/F5u49hgw022GCD\n" +
                        "Hy6GtWhECGEE7gcagW1AHeAEyuarBIEAcA3oBy4CfaqqytX2bVUFEELsBDqAJwBPnrdrwEngLVVV\n" +
                        "rxbbtxSrIoAQogHoBPYUyeRpoFNV1S+LZC9NUQUQQjwAvATsL6bdBZwCXlRV9T/FMlgUAYQQO4BX\n" +
                        "Wb3AFyKB94GXiyHEigQQQmwHDgNPrtRWAUjgPeAVVVWVQo0U5LQQ4sfMBf7zfG04nU7Ky8txOp1o\n" +
                        "moamaYW4sJAE8DfmesSNfG/Oy3khxFbg98wFbtJ7X21tLU899RSPPPIIdXV1i8pCoRAXLlzgnXfe\n" +
                        "4eLFi/m4s5QY8BfgNVVVb+q9SZcAQoga4HdAO2DRc09paSmtra20tbXR0NCQvn727FlsNhuNjY30\n" +
                        "9vbS1NTEli1bAPD5fPh8Pr2+L0cM6AGOqKo6kqtyVgGEENXMPfFfojPwXbt20dbWxv79+ykrK1tU\n" +
                        "FolE6Orq4o033qCkpASHw0F3dzctLS0AJBIJ6uvrCQaDeprKRRj4M3A0mxDGTBeFENVCiOPADeAQ\n" +
                        "OYKvqqqio6ODjz/+mDNnztDW1vad4AGSySSffPIJ9fX1PP3000SjUQYHB9PlJpOJ8+fPc+DAAcxm\n" +
                        "s64os2AHXgBuCCFeF0JkXIgt6gFCiFLgReDX8way0tTURHt7O83NzbodTiQSGI1GDAYD8Xh82fsG\n" +
                        "Bwc5evQoH3zwAclkUpftHASBPwKvq6oaSV1MCyCEqAXOAPfqsdbR0cHhw4fz9iIWi3HlyhU2b95M\n" +
                        "RUVFzvrXrl2jq6urmEJcAh5VVdUP8wIIIZzAl0CtXis9PT20trbmrDc+Ps6nn36a/ly6dIlIJILD\n" +
                        "4eDZZ5/l4MGDOJ3OnHYuX77MiRMnGB0dRVVVvvnmm5UIMgD8RFXVUEqAPzC3hNWN2+2ms7OT1tZW\n" +
                        "bDYbAFJKBgYG0sF+9tln3LiRfWp2uVw888wzHDp0KOO4sRyBQICPPvqIY8eO8e233+bjeorfqKrq\n" +
                        "SwnwT6CpECtmsxkhBHa7neHhYWZnZwsxQ2VlJR0dHbS3t1NaWrqobHR0lLNnz7Jnzx5GRkbo7+/n\n" +
                        "wIEDmEwmbt26xeOPP87ly5fzbfLvqqruTc0CBc878XicoaEhrl+/XnDwABMTExw5coRdu3bx9ttv\n" +
                        "I+XtowCTaW7NZTabMZvNGAy3x+5NmzZx8ODBQpqcgdvT4MlCHS82ExMTaJq2KMjR0VFOnTrFyMgI\n" +
                        "X3zxBR9++CGJRCJd3tfXV0hTJ2F+ENy3b59LURT/1NSUY2Xur5zW1lZ6enp01Y3FYnR3d3P8+PFF\n" +
                        "PSYXlZWVgfr6+h+dPn06ZAQwm80vbNu2zXHnnXcW5nWRaGho4MSJE7rqfvXVV7S0tHDs2LG8gq+u\n" +
                        "rqaurs7F3AJv7icgpdxrMBi455572LFjh65pqdhs3bqVd999F6vVmrVePB7H5/Oxd+9e+vv7ddt3\n" +
                        "uVzcd999bN68GYPBQDKZ3AdgBkgmk/emBppNmzaxfft2pqenuXnzJjMzMwUHpRePx0Nvby/l5eVZ\n" +
                        "6w0MDPDcc8/lNeI7nU68Xu93HmooFNoJ8wLE4/Epk8nkXHrjzp07mZqawu/3r6oQnZ2d1NTULFue\n" +
                        "TCZ588038fl8RKNRXTYdDgc1NTXL9ubp6en/wrwAsVhs0mQy1WRal5eXl1NeXs7k5CR+v79YO7VF\n" +
                        "ZFssXb9+neeff57PP/9cly2Hw4HX683amwKBAJOTk7cFAAiHw9hsNiyWzBu/iooKKioqmJiYwO/3\n" +
                        "EwqFdDmkh66uLgYGBnjssce4++67sVgsDA8Pc+7cOXp7e4lEIjltlJWV4fV6c+4vxsfHUVU1/d0A\n" +
                        "0Nzc/G/mXlxgMpmwWq3pxUc2Q36/f0WLn2JQWlqK1+ulsrIya71gMIiqqgv9/ZeiKLu/0+cTiQSz\n" +
                        "s7M5hXC73bjdbsbGxvD7/YTD4ZXGkhclJSV4vV7cbnfWeqFQCE3Tlv3pLruJXyiEzWbDaMx4doLH\n" +
                        "40kLMTQ0tOpC2O12vF4vHk/2F02zs7NompZz8M55ipFIJAiFQpjNZqxWa0YhDAYDd9xxBx6PB03T\n" +
                        "GBoa0j1a68Vut3PXXXfh8XgWLZOXEg6HUVVV96yl+9wpHo+nT3CyCSGEoKqqCk3TGB4e1jWAZcNu\n" +
                        "t1NdXU1VVVXOwDVN49atW3nZz/vgLR8hhBBMTU0xPj5OIBDQ3StsNhsulwu3243L5cpaNxKJoGka\n" +
                        "09PT+YYCFCBAipQQFosFi8Wy7BiRWkeknA2Hw4TDYeLxePpEx2g0YjabsdvtlJSU5FwOA0SjUTRN\n" +
                        "IxAIFBoCsAIBUsRiMaLRKGazOetgCXNPNvV0V9Le2NgYgUCgKGeEKxYgRUoIq9WaU4hCiMfjjI2N\n" +
                        "MT09TTKZzGsHmI2iCZAi1c1tNht2u33FQsRiMSYnJ4v2xJdSdAFShMNhZmdnsVgsWK3WZQfMTKSm\n" +
                        "3pmZGYLBIFLKoj3xpayaACmi0SiRSAQpJUajEZPJhNFoTL8ckVKSTCZJJBLE43Gi0SixWGxVg15I\n" +
                        "SoCVDaU6SQWZCm65zxoxC7cPRf+xVq2uI87BbQG6mXsR+kNhAHgL5gU4f/78NNACrFo62jriEvAz\n" +
                        "RVFCsCDLY3BwcLK2tvavzJ0R/JQ1GCDXmCDwGvALRVEmUxcz7i6am5urgd8Cv0LHa/Jcg1oxP5na\n" +
                        "y0EY+BPgUxRlbGlh1gyReSFyZoisUwHSGSKKoiybIaIrR6i5uTlrjtA6EyCdI5Qt8LwEWCBExiyx\n" +
                        "dSJAOktMUZTiZollEGJRnuD3LEA6T1BRlNXNE8wgxHbgsJTySSnlmgixQAAppXxPSvmKoihrmym6\n" +
                        "lIcffniHlPJVKeX+NRBASinfl1K+/PXXX3+/ucJL2b179wNSypdWUYhTwIv9/f3rK1t8KQ899FCD\n" +
                        "lLJTSrmnSIGfllJ2Xr16dX3/X2ApDz744E4pZYeU8gkppSfPoDUp5Ukp5VtXrlz5//rHyFIaGxuN\n" +
                        "Usr7pZSNUsptUso6KaVTSlk2H2xQShmQUl6TUvZLKS9KKfv6+vrWbG+8wQYbbPCD5H9+vxWfanub\n" +
                        "hwAAAABJRU5ErkJggg==");

        content.addChildren(logo);
        content.addChildren(new HTMLObject("h1", "Registrierungsbestätigung").setAttribute("class", "title"));
        content.addChildren(new HTMLObject("p", "Cool! Du hast es fast geschafft. Nur noch kurz auf diesen Link hier drücken und Dein Konto bei GameHubOne ist erstellt."));
        content.addChildren(new HTMLObject("a", "Mail bestätigen").setAttribute("class", "button").setAttribute("href", "https://api.gamehub.one/confirmation/register/" + values.get("confirmationId")));

        HTMLObject info = new HTMLObject("p");
        info.setAttribute("class", "info");
        info.addChildren(new HTMLObject("b", "Warum erhalte ich diese Mail?"));
        info.addChildren("Jemand hat sich mit deiner Mail Adresse einen Account auf ");
        info.addChildren(new HTMLObject("a", "GameHub.one").setAttribute("href", "https://gamehub.one/"));
        info.addChildren(" gemacht. Falls Du das nicht warst, kannst Du diese Mail ignorieren.");

        content.addChildren(info);

        content.addChildren(new HTMLObject("p", "Bitte antworte nicht auf diese Mail.").setAttribute("class", "info center"));

        container.addChildren(content);
        body.addChildren(container);

        return html;
    }

}
