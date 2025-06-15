-- auto-generated definition
create table admin
(
    id       int(50) auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    gender   varchar(32)  not null,
    age      varchar(255) not null,
    phone    varchar(255) not null
)
    engine = InnoDB;

-- auto-generated definition
create table arepair
(
    id            int(255) auto_increment
        primary key,
    item          varchar(255) not null,
    resident      varchar(255) not null,
    address       varchar(255) not null,
    breakdown     varchar(255) not null,
    preditExpense varchar(255) not null,
    expense       varchar(255) not null,
    isSolve       varchar(255) not null
)
    engine = InnoDB;

-- auto-generated definition
create table payannounce
(
    id       int(255) auto_increment
        primary key,
    item     varchar(255) not null,
    expense  varchar(255) not null,
    deadline varchar(255) null
)
    engine = InnoDB;

-- auto-generated definition
create table paystatus
(
    id         int(255) auto_increment
        primary key,
    username   varchar(255) not null,
    address    varchar(255) not null,
    is_pay_off varchar(255) not null
)
    engine = InnoDB;

-- auto-generated definition
create table resident
(
    id       int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    address  varchar(255) not null,
    vehicle  varchar(255) null
)
    engine = InnoDB;

-- 创建物业费用表
CREATE TABLE IF NOT EXISTS property_fee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    resident_id INT NOT NULL,
    FOREIGN KEY (resident_id) REFERENCES resident(id)
);

