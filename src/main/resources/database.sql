-- DATABASE RESET
DROP TABLE IF EXISTS company_data CASCADE;
DROP TABLE IF EXISTS app_settings CASCADE;
DROP TABLE IF EXISTS app_role CASCADE;
DROP TABLE IF EXISTS app_privileges CASCADE;
DROP TABLE IF EXISTS role_privileges CASCADE;
DROP TABLE IF EXISTS legal_entity_type CASCADE;
DROP TABLE IF EXISTS contact_data CASCADE;
DROP TABLE IF EXISTS country CASCADE;
DROP TABLE IF EXISTS app_user CASCADE;
DROP TABLE IF EXISTS client_type CASCADE;
DROP TABLE IF EXISTS business_branch CASCADE;
DROP TABLE IF EXISTS business_category CASCADE;
DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS tax_info CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS client_branch CASCADE;
DROP TABLE IF EXISTS client_category CASCADE;
DROP TABLE IF EXISTS client_representative CASCADE;
DROP TABLE IF EXISTS power_type CASCADE;
DROP TABLE IF EXISTS place_type CASCADE;
DROP TABLE IF EXISTS event_status CASCADE;
DROP TABLE IF EXISTS booking_status CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS event_equipment CASCADE;
DROP TABLE IF EXISTS equipment_category CASCADE;
DROP TABLE IF EXISTS equipment CASCADE;
DROP TABLE IF EXISTS equipment_photo CASCADE;

-- APPLICATION RELATED


CREATE TABLE company_meta_data (
    id smallserial PRIMARY KEY,
    full_name character varying(250) NOT NULL,
    short_name character varying(250) NOT NULL,
    legal_entity_type_id integer,
    regon character varying(20),
    pesel character varying(20),
    nip character varying(20),
    krs character varying(20),
    insurance character varying(20),
    in_use boolean NOT NULL DEFAULT true,
    notes text,
    street character varying(100),
    street_number character varying(10),
    postal_code character varying(10),
    city character varying(100),
    country_id integer,
    telephone character varying(20),
    email character varying(20)
);

CREATE TABLE app_settings (
    id serial PRIMARY KEY,
    resources_URI character varying(100)
);

CREATE TABLE app_role (
    id serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE app_privileges (
    id serial PRIMARY KEY,
    name character varying(20)
);

CREATE TABLE role_privileges (
    id serial PRIMARY KEY,
    app_role_id integer,
    app_privileges_id integer,
    _create boolean NOT NULL DEFAULT false,
    _read boolean NOT NULL DEFAULT false,
    _update boolean NOT NULL DEFAULT false,
    _delete boolean NOT NULL DEFAULT false
);

CREATE TABLE legal_entity_type (
    id serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE contact_data (
    id serial PRIMARY KEY,
    email character varying(20),
    phone character varying(20)
);

CREATE TABLE country (
    id serial PRIMARY KEY,
    name character varying(100)
);

CREATE TABLE app_user (
    id uuid NOT NULL PRIMARY KEY,
    login character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    contact_data_id integer,
    app_role_id integer
);

-- DATA-RELATED
-- CLIENT RELATED

CREATE TABLE client_type (
    id serial PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE business_branch (
    id serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE business_category (
    id serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE client (
    id uuid PRIMARY KEY NOT NULL,
    full_name character varying(200) NOT NULL,
    short_name character varying(100) NOT NULL,
    contact_data_id integer,
    is_active boolean NOT NULL DEFAULT true,
    client_type_id integer,
    notes text,
    legal_entity_type_id integer,
    tax_info_id integer
);

CREATE TABLE tax_info (
    id serial PRIMARY KEY,
    regon character varying(20),
    pesel character varying(20),
    nip character varying(20),
    krs character varying(20),
    insurance character varying(20)
);


CREATE TABLE address (
    id serial PRIMARY KEY,
    street character varying(100),
    street_number character varying(10),
    postal_code character varying(10),
    city character varying(100),
    country_id integer,
    isPrimary boolean NOT NULL DEFAULT true,
    client_id uuid
);

CREATE TABLE client_branch (
    id serial PRIMARY KEY,
    client_id UUID,
    business_branch_id integer
);

CREATE TABLE client_category (
    id serial PRIMARY KEY,
    client_id UUID,
    business_category_id integer
);

CREATE TABLE client_representative (
    id UUID PRIMARY KEY,
    first_name character varying(100) NOT NULL,
    last_name character varying(100),
    contact_data_id integer,
    client_id uuid
);

-- EVENTS RELATED

CREATE TABLE power_type (
    id serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE place_type (
    id serial PRIMARY KEY,
    name character varying(50)
);

CREATE TABLE event_status (
    id smallserial PRIMARY KEY,
    name character varying(20)
);

CREATE TABLE booking_status (
    id smallserial PRIMARY KEY,
    name character varying(20)
);

CREATE TABLE events (
    id uuid PRIMARY KEY,
    name character varying(255) NOT NULL,
    creation_date date NOT NULL,
    event_start_date date NOT NULL,
    event_end_date date NOT NULL,
    event_place character varying(100),
    description text,
    event_status_id integer,
    distance smallint,
    required_area smallint,
    power_type_id integer,
    place_type_id integer,
    notes character varying(50)
);

CREATE TABLE event_equipment (
    id serial PRIMARY KEY,
    equipment_id uuid NOT NULL,
    event_id uuid,
    booking_status_id integer
);

-- EQUIPMENT RELATED

CREATE TABLE equipment_category (
    id serial PRIMARY KEY,
    name character varying(100) NOT NULL,
    sorting_group integer NOT NULL DEFAULT 0
);

CREATE TABLE equipment (
    id uuid PRIMARY KEY,
    sorting_id smallint NOT NULL DEFAULT 1,
    name character varying(100),
    notes text,
    width smallint,
    length smallint,
    height smallint,
    weight smallint,
    power_required smallint,
    staff_needed smallint,
    minimum_age smallint,
    max_participants smallint
    equipment_category_id integer,
    in_use boolean NOT NULL DEFAULT true
);

CREATE TABLE equipment_photo (
    id serial PRIMARY KEY,
    equipment_id uuid NOT NULL,
    photo_URI character varying(250) NOT NULL
);

-- CONSTRAINTS

ALTER TABLE equipment_photo ADD CONSTRAINT FK_equipment FOREIGN KEY (equipment_id) REFERENCES equipment(id);
ALTER TABLE equipment ADD CONSTRAINT FK_equipment_category FOREIGN KEY (equipment_category_id)  REFERENCES equipment_category(id);
ALTER TABLE event_equipment ADD CONSTRAINT FK_equipment FOREIGN KEY (equipment_id)  REFERENCES equipment(id);
ALTER TABLE event_equipment ADD CONSTRAINT FK_event FOREIGN KEY (event_id)  REFERENCES events(id);
ALTER TABLE event_equipment ADD CONSTRAINT FK_booking_status FOREIGN KEY (booking_status_id)  REFERENCES booking_status(id);
ALTER TABLE events ADD CONSTRAINT FK_event_status FOREIGN KEY (event_status_id) REFERENCES event_status(id);
ALTER TABLE events ADD CONSTRAINT FK_power_type FOREIGN KEY (power_type_id) REFERENCES power_type(id);
ALTER TABLE events ADD CONSTRAINT FK_place_type FOREIGN KEY (place_type_id) REFERENCES place_type(id);
ALTER TABLE client_representative ADD CONSTRAINT FK_contact_data FOREIGN KEY (contact_data_id) REFERENCES contact_data(id);
ALTER TABLE client_representative ADD CONSTRAINT FK_client FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE client_category ADD CONSTRAINT FK_business_category FOREIGN KEY (business_category_id) REFERENCES business_category(id);
ALTER TABLE client_category ADD CONSTRAINT FK_client FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE client_branch ADD CONSTRAINT FK_business_branch FOREIGN KEY (business_branch_id) REFERENCES business_branch(id);
ALTER TABLE client_branch ADD CONSTRAINT FK_client FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE address ADD CONSTRAINT FK_country FOREIGN KEY (country_id) REFERENCES country(id);
ALTER TABLE address ADD CONSTRAINT FK_client FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE client ADD CONSTRAINT FK_contact_data FOREIGN KEY (contact_data_id) REFERENCES contact_data(id);
ALTER TABLE client ADD CONSTRAINT FK_client_type FOREIGN KEY (client_type_id) REFERENCES client_type(id);
ALTER TABLE client ADD CONSTRAINT FK_legal_entity_type FOREIGN KEY (legal_entity_type_id) REFERENCES legal_entity_type(id);
ALTER TABLE client ADD CONSTRAINT FK_tax_info FOREIGN KEY (tax_info_id) REFERENCES tax_info(id);
ALTER TABLE company_data ADD CONSTRAINT FK_legal_entity_type FOREIGN KEY (legal_entity_type_id) REFERENCES legal_entity_type(id);
ALTER TABLE company_data ADD CONSTRAINT FK_country FOREIGN KEY (country_id) REFERENCES country(id);
ALTER TABLE app_user ADD CONSTRAINT FK_contact_data FOREIGN KEY (contact_data_id) REFERENCES contact_data(id);
ALTER TABLE app_user ADD CONSTRAINT FK_app_role FOREIGN KEY (app_role_id) REFERENCES app_role(id);
ALTER TABLE role_privileges ADD CONSTRAINT FK_app_role FOREIGN KEY (app_role_id) REFERENCES app_role(id);
ALTER TABLE role_privileges ADD CONSTRAINT FK_app_privileges FOREIGN KEY (app_privileges_id) REFERENCES app_privileges(id);

-- POPULATE

INSERT INTO app_role (name) VALUES
('admin'),
('salesman'),
('service'),
('guest');

INSERT INTO app_privileges (name) VALUES
('admin'),
('clients'),
('events'),
('equipment'),
('invoices'),
('contracts'),
('employees');
