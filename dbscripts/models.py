from app import db
from config import get_cur_time, get_md5,user_status_true,user_status_false, NoticeRead, Echarts

# 用户表
class User(db.Model):  # 创建的类对应数据库的表以及对表的相关操作
    __tablename__ = 'user'
    __table_args__ = {
        'autoload': True,
        'autoload_with': db.engine
    }

    @classmethod
    def user_login(self, account, pwd):  # 用户登录
        query_user = db.session.query(User)
        all_user = query_user.all()
        flag = False
        user_id = 0
        username = ''
        identity = ''
        user_status = ''
        create_time = ''
        for user in all_user:
            if user.account == account and user.password == get_md5(pwd):
                user_id = user.id
                username = user.username
                identity = user.identity
                user_status = user.user_status
                create_time = user.create_time
                break
        if user_status != user_status_true: return {}
        flag = True
        if flag == True:
            flag = False
            login_time = get_cur_time()
            # 更新用户登录时间
            user = db.session.query(User).filter(User.account == account).first()
            user.login_time = login_time
            db.session.commit()
            return {'user_id': user_id, 'username': username, 'identity': identity, 'user_status': user_status, 'create_time': create_time, 'login_time': login_time}
        else:
            return {}
