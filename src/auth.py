import string
import random
from flask import Blueprint, render_template, jsonify, redirect, url_for
from exts import mail, db
from flask_mail import Message
from flask import request
from models import EmailCaptchaModel
from .forms import RegisterForm
from models import UserModel
from werkzeug.security import generate_password_hash

# /auth
bp = Blueprint("auth", __name__, url_prefix="/auth")


@bp.route("/login")
def login():
    return "登录页面"


@bp.route("/register", methods=['GET', 'POST'])
def register():
    if request.method == 'GET':
      return render_template("register.html")
    else:
        form = RegisterForm(request.form)
        if form.validate():
            email = form.email.data
            username = form.username.data
            password = form.password.data
            user = UserModel(email=email, username=username, password=generate_password_hash(password))
            db.session.add(user)
            db.session.commit()
            return redirect(url_for("auth.login"))
        else:
            print(form.errors)
            return redirect(url_for("auth.register"))


@bp.route("/captcha/email")
def get_email_captcha():
    email = request.args.get("email")
    source = string.digits * 6
    captcha = random.sample(source, 6)
    captcha = "".join(captcha)
    message = Message(subject="验证码", recipients=[email], body=f"验证码：{captcha}")
    mail.send(message)
    email_captcha = EmailCaptchaModel(email=email, captcha=captcha)
    db.session.add(email_captcha)
    db.session.commit()
    return jsonify({"code": 200, "message": "", "data": None})


@bp.route("/mail/test")
def mail_test():
    message = Message(subject="邮箱测试", recipients=["3614700373@qq.com"], body="测试一下")
    mail.send(message)
    return "susess"
