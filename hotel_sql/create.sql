CREATE TABLE customer (
  id int(10) NOT NULL AUTO_INCREMENT,
  first_name varchar(25) NOT NULL,
  last_name varchar(25) NOT NULL,
  cell_phone_number int(11) NOT NULL,
  temp_phone_number int(11),
  birthday date NOT NULL,
  login_id varchar(10) NOT NULL,
  login_pw varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE department (
  id int(1) NOT NULL,
  name varchar(20) NOT NULL,
  task varchar(20) NOT NULL,
  office varchar(20) NOT NULL,
  phone_number int(11) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE staff (
  id int(7)  NOT NULL,
  department_id  int(1) NOT NULL,
  first_name varchar(25) NOT NULL,
  last_name varchar(25) NOT NULL,
  gender varchar(6) NOT NULL,
  birthday date NOT NULL,
  email varchar(40) NOT NULL UNIQUE,
  position varchar(15) NOT NULL,
  salary int(10) NOT NULL,
  contract_start date NOT NULL,
  contract_end date,
  cell_phone_number int(11) NOT NULL,
  office_phone_number int(11) NOT NULL,
  home_phone_number int(11) NOT NULL,
  login_pw varchar(20) NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(department_id) REFERENCES department(id) on update cascade on delete cascade
);

CREATE TABLE vacation (
  staff_id int(7) NOT NULL,
  start_date date NOT NULL,
  end_date date NOT NULL,
  PRIMARY KEY (staff_id),
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade
);

CREATE TABLE parking (
  id int(10) NOT NULL AUTO_INCREMENT,
  car_num varchar(10) NOT NULL,
  customer_id int(10) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(customer_id) REFERENCES customer(id) on update cascade on delete cascade
);

CREATE TABLE room(
  room_number int(3) NOT NULL,
  grade varchar(10) NOT NULL,
  bed_type varchar(10) NOT NULL,
  capacity int(1) NOT NULL,
  view varchar(10) NOT NULL,
  price int(10) NOT NULL,
  staff_id int(7) NOT NULL,
  PRIMARY KEY (room_number),
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade 
);

CREATE TABLE room_reservation (
  id int(10) NOT NULL AUTO_INCREMENT,
  num_of_adult int(5) NOT NULL,
  num_of_children int(5) NOT NULL,
  check_in date NOT NULL,
  check_out date NOT NULL,
  breakfast boolean NOT NULL,
  requirement text,
  room_id int(2) NOT NULL,
  customer_id int(10) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(room_id) REFERENCES room(room_number) on update cascade on delete cascade,
  FOREIGN KEY(customer_id) REFERENCES customer(id) on update cascade on delete cascade
);

CREATE TABLE daily_room_service (
  room_reservation_id int(10) NOT NULL,
  date date NOT NULL,
  service_description text NOT NULL,
  PRIMARY KEY (date, room_reservation_id),
  FOREIGN KEY(room_reservation_id) REFERENCES room_reservation(id) on update cascade on delete cascade
);

CREATE TABLE valuable (
  id int(10) NOT NULL AUTO_INCREMENT,
  content varchar(80) NOT NULL,
  custom_id int(10) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(custom_id) REFERENCES customer(id) on update cascade on delete cascade
);

CREATE TABLE valuable_staff (
  valuable_id int(10) NOT NULL,
  staff_id int(7) NOT NULL,
  receive_date date NOT NULL,
  receive_time time NOT NULL,
  give_date date NOT NULL,
  give_time time NOT NULL,
  PRIMARY KEY (valuable_id),
  FOREIGN KEY(valuable_id) REFERENCES valuable(id) on update cascade on delete cascade,
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade
);

CREATE TABLE parking_staff (
  parking_id int(10) NOT NULL,
  staff_id int(7) NOT NULL,
  receive_date date,
  receive_time time,
  give_date date,
  give_time time,
  PRIMARY KEY (parking_id),
  FOREIGN KEY(parking_id) REFERENCES parking(id) on update cascade on delete cascade,
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade
);

CREATE TABLE daily_status (
  staff_id int(7) NOT NULL,
  daily_date date NOT NULL,
  daily_attend_time time,
  daily_leave_time time,
  PRIMARY KEY (staff_id, daily_date),
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade
);

CREATE TABLE status (
  staff_id int(7) NOT NULL,
  late_count int(10) NOT NULL,
  absence_count int(10) NOT NULL,
  trouble_count int(10) NOT NULL,
  PRIMARY KEY (staff_id),
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade
);

CREATE TABLE work (
  staff_id int(7) NOT NULL,
  days varchar(8) NOT NULL,
  hours int(2) NOT NULL,
  PRIMARY KEY (staff_id),
  FOREIGN KEY(staff_id) REFERENCES staff(id) on update cascade on delete cascade
);

CREATE TABLE daily_room_manage (
  room_reservation_id int(10) NOT NULL,
  date date NOT NULL,
  cleaningYN boolean NOT NULL,
  disturbYN boolean NOT NULL,
  PRIMARY KEY (room_reservation_id, date),
  FOREIGN KEY(room_reservation_id) REFERENCES room_reservation(id) on update cascade on delete cascade
);

