-- APPLICATION RELATED

CREATE TABLE user_privilege (
    id serial PRIMARY KEY,
    user_id uuid REFERENCES app_user(id) NOT NULL,
    access_admin_console boolean NOT NULL DEFAULT false,
    edit_client boolean NOT NULL DEFAULT false,
    view_client boolean NOT NULL DEFAULT false,
    edit_event boolean NOT NULL DEFAULT false,
    view_event boolean NOT NULL DEFAULT false,
    edit_invoice boolean NOT NULL DEFAULT false,
    view_invoice boolean NOT NULL DEFAULT false,
    edit_contract boolean NOT NULL DEFAULT false,
    view_contract boolean NOT NULL DEFAULT false,
    edit_equipment boolean NOT NULL DEFAULT false,
    view_equipment boolean NOT NULL DEFAULT false,
)

CREATE TABLE app_user (
    id uuid NOT NULL PRIMARY KEY,
    login character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    contact_data_id integer REFERENCES contact_data(id)
);

CREATE TABLE app_options (
    id serial PRIMARY KEY,
    an_option character varying(200)
);

CREATE TABLE app_owner (
    id smallserial PRIMARY KEY,
    full_name character varying(250) NOT NULL,
    short_name character varying(250) NOT NULL,
    address_id integer REFERENCES primary_address(id),
    legal_entity_type_id integer REFERENCES legal_entity_type(id),
    tax_info_id integer REFERENCES tax_info(id),
    in_use boolean NOT NULL,
);


-- DATA-RELATED
-- COMMON

CREATE TABLE contact_data (
    id serial PRIMARY KEY,
    email character varying(20),
    phone character varying(20),
);

CREATE TABLE country (
    id serial PRIMARY KEY,
    name character varying(100)
);

CREATE TABLE primary_address (
    id serial PRIMARY KEY,
    street character varying(100),
    street_number character varying(10),
    postal_code character varying(10),
    city character varying(100),
    country_id integer REFERENCES country(id)
);

CREATE TABLE secondary_address (
    id serial PRIMARY KEY,
    street character varying(100),
    street_number character varying(10),
    postal_code character varying(10),
    city character varying(100),
    country_id integer REFERENCES country(id)
);

-- CLIENT RELATED

CREATE TABLE client_type (
    id serial PRIMARY KEY,
    name character varying(100) NOT NULL
);

CREATE TABLE tax_info (
    id serial PRIMARY KEY,
    regon character varying(20),
    pesel character varying(20),
    nip character varying(20),
    krs character varying(20)
);

CREATE TABLE legal_entity_type (
    id serial PRIMARY KEY,
    name character varying(50)
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
    id uuid NOT NULL;
    String full_name character varying(200) NOT NULL,
    String short_name character varying(100) NOT NULL,
    primary_address_id integer REFERENCES primary_address(id),
    secondary_address_id integer REFERENCES secondary_address(id),
    contact_data_id integer REFERENCES contact_data(id),
    is_active boolean NOT NULL DEFAULT true,
    client_type_id NOT NULL REFERENCES client_type(id),
    notes text,
    legal_entity_type_id integer REFERENCES legal_entity_type(id),
    tax_info_id integer REFERENCES tax_info_type(id)
);

CREATE TABLE client_branch (
    id serial PRIMARY KEY,
    client_id UUID REFERENCES client(id),
    business_branch_id integer REFERENCES business_branch(id)
);

CREATE TABLE client_category (
    id serial PRIMARY KEY,
    client_id UUID REFERENCES client(id),
    business_category_id integer REFERENCES buisiness_category(id)
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

CREATE TABLE logistics (
    id serial PRIMARY KEY,
    distance smallint,
    notes text,
    required_area smallint,
    power_type_id integer REFERENCES power_type(id),
    place_type_id integer REFERENCES place_type(id),
    name character varying(50)
);

CREATE TABLE event_status (
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
    notes text,
    event_status_id integer REFERENCES event_status(id),
    logistics_id integer REFERENCES logistics(id),
);

CREATE TABLE event_equipment (
    id serial PRIMARY KEY,
    equipment_id uuid REFERENCES equimpent(id) NOT NULL;
    event_id uuid REFERENCES events(id),
    event_status_id integer REFERENCES event_status(id)
);


-- EQUIPMENT RELATED

CREATE TABLE equipment_category (
    id serial PRIMARY KEY,
    name character varying(100) NOT NULL,
    sorting_group integer NOT NULL DEFAULT 0
);

CREATE TABLE equipment_data (
    id serial PRIMARY KEY,
    equipment_id integer REFERENCES equipment(id),
    width smallint,
    length smallint,
    height smallint,
    weight smallint,
    power_required smallint,
    staff_needed smallint,
    minimum_age smallint,
    max_participants smallint
);

CREATE TABLE equipment (
    id uuid PRIMARY KEY,
    sorting_id smallint NOT NULL DEFAULT 1,
    name character varying(100),
    notes text,
    equipment_data_id integer REFERENCES equipment_data(id),
    equipment_category_id integer REFERENCES equipment_category(id),
    in_use boolean NOT NULL DEFAULT true
);

CREATE TABLE equipment_photo (
    id serial PRIMARY KEY,
    equipment_id uuid REFERENCES equipment(id) NOT NULL,
    photo_URI character varying(250) NOT NULL
);