CREATE DATABASE IF NOT EXISTS rmage_test_db;
CREATE USER IF NOT EXISTS 'rmage_test_user'@'%' IDENTIFIED BY 'test_password';
GRANT ALL PRIVILEGES ON rmage_test_db.* TO 'rmage_test_user'@'%';
FLUSH PRIVILEGES;