import smtplib
import os
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

def send_email(subject, body, to, user):
    msg = MIMEMultipart()
    msg['From'] = user
    msg['To'] = ", ".join(to)
    msg['subject'] = subject

    msg.attach(MIMEText(body, 'plain'))

    server = smtplib.SMTP('smtp.manulife.com', 25)
    # server.starttls()
    # server.login(user, password)

    text = msg.as_string()
    server.sendmail(user, to, text)
    server.quit()

def main():
    subject = "API definition found in your recent commit"
    body = os.environ["EMAIL_BODY"]
    to = [os.environ["COMMITER_EMAIL"], "bruno_fernando_cantanhede_morgado@manulife.ca"]
    user = os.environ["USER"]
    # password = os.environ["PASSWORD"]

    send_email(subject, body, to, user)

if __name__ == "__main__":
    main()