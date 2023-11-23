
HOSTNAME = "localhost"
PORT = 3306
USERNAME= "root"
PASSWORD = "tanyihao666"
DATABASE = "library"
DB_URI = 'mysql+pymysql://{}:{}@{}:{}/{}?charset=utf8'.format(USERNAME,PASSWORD,HOSTNAME,PORT,DATABASE)
SQLALCHEMY_DATABASE_URI = DB_URI

MAIL_SERVER = "smtp.qq.com"
MAIL_USE_SSL = True
MAIL_PORT = 465
MAIL_USERNAME = "854247283@qq.com"
MAIL_PASSWORD = "truvgaqovcubbcde"
MAIL_DEFAULT_SENDER = "854247283@qq.com"


#zhiliaooa_course