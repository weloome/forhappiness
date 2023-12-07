CREATE TABLE `customer` (
                            `id` int unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
                            `email` varchar(255) UNIQUE NOT NULL COMMENT '이메일 주소',
                            `password_hash` varchar(255) COMMENT '비밀번호',
                            `first_name` varchar(255) NOT NULL COMMENT '고객 이름-이름',
                            `middle_name` varchar(255) NOT NULL COMMENT '고객 이름',
                            `last_name` varchar(255) NOT NULL COMMENT '고객 이름-성',
                            `full_name` varchar(255) NOT NULL COMMENT '고객 성이',
                            `phone` varchar(150) UNIQUE NOT NULL COMMENT '휴대전화번호',
                            `dob` date COMMENT '생년월일',
                            `gender` smallint COMMENT '성별 1:여성, 2:남성, 3:선택안함',
                            `created_at` timestamp default current_timestamp
);