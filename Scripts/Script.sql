create database googlephotos;



-- 회원
CREATE TABLE user (
	uno       INTEGER      NOT null, -- 회원번호
	uid       VARCHAR(50)  NOT NULL, -- 아이디
	uname     VARCHAR(50)  NOT NULL, -- 이름
	uemail    VARCHAR(100) NULL,     -- 이메일
	uphone    VARCHAR(100) NULL,     -- 전화번호
	upassword VARCHAR(100) NOT NULL  -- 비밀번호
);

-- 회원
ALTER TABLE user
	ADD
		
		PRIMARY KEY (
			uno -- 회원번호
		);

