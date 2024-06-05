--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: architecture_firm; Type: SCHEMA; Schema: -; Owner: ghassanq
--

CREATE SCHEMA architecture_firm;


ALTER SCHEMA architecture_firm OWNER TO ghassanq;

--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- Name: genderType; Type: TYPE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TYPE architecture_firm."genderType" AS ENUM (
    'M',
    'F'
);


ALTER TYPE architecture_firm."genderType" OWNER TO ghassanq;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Customer; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Customer" (
    "ID" character varying(9) NOT NULL,
    "First_name" character varying(15) NOT NULL,
    "Last_name" character varying(15) NOT NULL,
    "User_name" text NOT NULL,
    "Password" text NOT NULL,
    "Phone_number" character varying(10) NOT NULL,
    "Email_address" character varying(30) NOT NULL
);


ALTER TABLE architecture_firm."Customer" OWNER TO ghassanq;

--
-- Name: Department; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Department" (
    "Department_ID" character varying(9) NOT NULL,
    "ManagerEng_ID" character varying(9)
);


ALTER TABLE architecture_firm."Department" OWNER TO ghassanq;

--
-- Name: Engineer_project; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Engineer_project" (
    "Serial" integer NOT NULL,
    "Engineer_ID" character varying(9),
    "Project_ID" character varying(9)
);


ALTER TABLE architecture_firm."Engineer_project" OWNER TO ghassanq;

--
-- Name: Engineer_project_Serial_seq; Type: SEQUENCE; Schema: architecture_firm; Owner: ghassanq
--

CREATE SEQUENCE architecture_firm."Engineer_project_Serial_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE architecture_firm."Engineer_project_Serial_seq" OWNER TO ghassanq;

--
-- Name: Engineer_project_Serial_seq; Type: SEQUENCE OWNED BY; Schema: architecture_firm; Owner: ghassanq
--

ALTER SEQUENCE architecture_firm."Engineer_project_Serial_seq" OWNED BY architecture_firm."Engineer_project"."Serial";


--
-- Name: Full-time_Engineer; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Full-time_Engineer" (
    "ID" character varying(9) NOT NULL,
    "First_name" character varying(15) NOT NULL,
    "Last_name" character varying(15) NOT NULL,
    "User_name" text NOT NULL,
    "Password" text NOT NULL,
    "Birthdate" date,
    "Sex" architecture_firm."genderType" NOT NULL,
    "Phone_number" character varying(10) NOT NULL,
    "Email_address" character varying(30) NOT NULL,
    "Department_ID" character varying(9),
    "Work_hours" character varying(20),
    "Degree" text,
    "Salary" integer DEFAULT 5000 NOT NULL,
    "isManager" boolean DEFAULT false NOT NULL
);


ALTER TABLE architecture_firm."Full-time_Engineer" OWNER TO ghassanq;

--
-- Name: Land; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Land" (
    "Land_ID" character varying(9) NOT NULL,
    "Owner_ID" character varying(9),
    "Soil_type" text,
    "Location" text,
    "Land_papers" boolean NOT NULL,
    "Area" integer
);


ALTER TABLE architecture_firm."Land" OWNER TO ghassanq;

--
-- Name: Project; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Project" (
    "Project_ID" character varying(9) NOT NULL,
    "Project_name" character varying(50) NOT NULL,
    "Land_ID" character varying(9),
    "Type" text,
    "Number_of_floors" integer,
    "Project_area" integer,
    "Description" text,
    "Department_ID" character varying(9),
    "Customer_ID" character varying(9),
    "Preset" integer DEFAULT 0 NOT NULL
);


ALTER TABLE architecture_firm."Project" OWNER TO ghassanq;

--
-- Name: Student_Project; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Student_Project" (
    "Serail" integer NOT NULL,
    "Project_ID" character varying(9),
    "Student_ID" character varying(9)
);


ALTER TABLE architecture_firm."Student_Project" OWNER TO ghassanq;

--
-- Name: Student_project_Serail_seq; Type: SEQUENCE; Schema: architecture_firm; Owner: ghassanq
--

CREATE SEQUENCE architecture_firm."Student_project_Serail_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE architecture_firm."Student_project_Serail_seq" OWNER TO ghassanq;

--
-- Name: Student_project_Serail_seq; Type: SEQUENCE OWNED BY; Schema: architecture_firm; Owner: ghassanq
--

ALTER SEQUENCE architecture_firm."Student_project_Serail_seq" OWNED BY architecture_firm."Student_Project"."Serail";


--
-- Name: Student_trainee; Type: TABLE; Schema: architecture_firm; Owner: ghassanq
--

CREATE TABLE architecture_firm."Student_trainee" (
    "ID" character varying(9) NOT NULL,
    "First_name" character varying(15) NOT NULL,
    "Last_name" character varying(15) NOT NULL,
    "User_name" text NOT NULL,
    "Password" text NOT NULL,
    "Birthdate" date,
    "Sex" architecture_firm."genderType" NOT NULL,
    "Phone_number" character varying(10) NOT NULL,
    "Email_address" character varying(30) NOT NULL,
    "Department_ID" character varying(9),
    "Work_hours" character varying(20),
    "College_year" integer,
    "Required_hours" integer NOT NULL,
    "Supervisor_ID" character varying(9)
);


ALTER TABLE architecture_firm."Student_trainee" OWNER TO ghassanq;

--
-- Name: Engineer_project Serial; Type: DEFAULT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Engineer_project" ALTER COLUMN "Serial" SET DEFAULT nextval('architecture_firm."Engineer_project_Serial_seq"'::regclass);


--
-- Name: Student_Project Serail; Type: DEFAULT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_Project" ALTER COLUMN "Serail" SET DEFAULT nextval('architecture_firm."Student_project_Serail_seq"'::regclass);


--
-- Name: Customer Customer_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Customer"
    ADD CONSTRAINT "Customer_pkey" PRIMARY KEY ("ID");


--
-- Name: Department Department_Eng_ID_key; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Department"
    ADD CONSTRAINT "Department_Eng_ID_key" UNIQUE ("ManagerEng_ID");


--
-- Name: Department Department_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Department"
    ADD CONSTRAINT "Department_pkey" PRIMARY KEY ("Department_ID");


--
-- Name: Full-time_Engineer Email_addressEng_unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Full-time_Engineer"
    ADD CONSTRAINT "Email_addressEng_unique" UNIQUE ("Email_address");


--
-- Name: Student_trainee Email_address_Student_unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_trainee"
    ADD CONSTRAINT "Email_address_Student_unique" UNIQUE ("Email_address");


--
-- Name: Customer Email_address_unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Customer"
    ADD CONSTRAINT "Email_address_unique" UNIQUE ("Email_address");


--
-- Name: Engineer_project Engineer_project_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Engineer_project"
    ADD CONSTRAINT "Engineer_project_pkey" PRIMARY KEY ("Serial");


--
-- Name: Full-time_Engineer Full-time_Engineer_User_name_key; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Full-time_Engineer"
    ADD CONSTRAINT "Full-time_Engineer_User_name_key" UNIQUE ("User_name");


--
-- Name: Full-time_Engineer Full-time_Engineer_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Full-time_Engineer"
    ADD CONSTRAINT "Full-time_Engineer_pkey" PRIMARY KEY ("ID");


--
-- Name: Land Land_ID_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Land"
    ADD CONSTRAINT "Land_ID_pkey" PRIMARY KEY ("Land_ID");


--
-- Name: Full-time_Engineer Phone_numberEng_unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Full-time_Engineer"
    ADD CONSTRAINT "Phone_numberEng_unique" UNIQUE ("Phone_number");


--
-- Name: Student_trainee Phone_number_Student_unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_trainee"
    ADD CONSTRAINT "Phone_number_Student_unique" UNIQUE ("Phone_number");


--
-- Name: Customer Phone_number_unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Customer"
    ADD CONSTRAINT "Phone_number_unique" UNIQUE ("Phone_number");


--
-- Name: Project Project_Project_name_key; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Project"
    ADD CONSTRAINT "Project_Project_name_key" UNIQUE ("Project_name");


--
-- Name: Project Project_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Project"
    ADD CONSTRAINT "Project_pkey" PRIMARY KEY ("Project_ID");


--
-- Name: Student_Project Student_project_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_Project"
    ADD CONSTRAINT "Student_project_pkey" PRIMARY KEY ("Serail");


--
-- Name: Student_trainee Student_trainee_User_name_key; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_trainee"
    ADD CONSTRAINT "Student_trainee_User_name_key" UNIQUE ("User_name");


--
-- Name: Student_trainee Student_trainee_pkey; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_trainee"
    ADD CONSTRAINT "Student_trainee_pkey" PRIMARY KEY ("ID");


--
-- Name: Customer Username_Unique; Type: CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Customer"
    ADD CONSTRAINT "Username_Unique" UNIQUE ("User_name");


--
-- Name: fki_Department_ID_fk; Type: INDEX; Schema: architecture_firm; Owner: ghassanq
--

CREATE INDEX "fki_Department_ID_fk" ON architecture_firm."Project" USING btree ("Department_ID");


--
-- Name: fki_Eng_manages_Dept_fk; Type: INDEX; Schema: architecture_firm; Owner: ghassanq
--

CREATE INDEX "fki_Eng_manages_Dept_fk" ON architecture_firm."Department" USING btree ("ManagerEng_ID");


--
-- Name: fki_Owner_ID_Land_ID_fk; Type: INDEX; Schema: architecture_firm; Owner: ghassanq
--

CREATE INDEX "fki_Owner_ID_Land_ID_fk" ON architecture_firm."Land" USING btree ("Owner_ID");


--
-- Name: Project Customer_ID_Project_ID_fk; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Project"
    ADD CONSTRAINT "Customer_ID_Project_ID_fk" FOREIGN KEY ("Customer_ID") REFERENCES architecture_firm."Customer"("ID");


--
-- Name: Full-time_Engineer Department_ID_Eng_ID_fk; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Full-time_Engineer"
    ADD CONSTRAINT "Department_ID_Eng_ID_fk" FOREIGN KEY ("Department_ID") REFERENCES architecture_firm."Department"("Department_ID");


--
-- Name: Project Department_ID_Project_ID_fk; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Project"
    ADD CONSTRAINT "Department_ID_Project_ID_fk" FOREIGN KEY ("Department_ID") REFERENCES architecture_firm."Department"("Department_ID");


--
-- Name: Student_trainee Department_ID_Student_ID_fk; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_trainee"
    ADD CONSTRAINT "Department_ID_Student_ID_fk" FOREIGN KEY ("Department_ID") REFERENCES architecture_firm."Department"("Department_ID");


--
-- Name: Department Eng_manages_Dept_fk; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Department"
    ADD CONSTRAINT "Eng_manages_Dept_fk" FOREIGN KEY ("ManagerEng_ID") REFERENCES architecture_firm."Full-time_Engineer"("ID");


--
-- Name: Engineer_project Engineer_project_Engineer_ID_fkey; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Engineer_project"
    ADD CONSTRAINT "Engineer_project_Engineer_ID_fkey" FOREIGN KEY ("Engineer_ID") REFERENCES architecture_firm."Full-time_Engineer"("ID");


--
-- Name: Engineer_project Engineer_project_Project_ID_fkey; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Engineer_project"
    ADD CONSTRAINT "Engineer_project_Project_ID_fkey" FOREIGN KEY ("Project_ID") REFERENCES architecture_firm."Project"("Project_ID");


--
-- Name: Project Land_ID_Project_ID_fk; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Project"
    ADD CONSTRAINT "Land_ID_Project_ID_fk" FOREIGN KEY ("Land_ID") REFERENCES architecture_firm."Land"("Land_ID");


--
-- Name: Land Land_Owner_ID_fkey; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Land"
    ADD CONSTRAINT "Land_Owner_ID_fkey" FOREIGN KEY ("Owner_ID") REFERENCES architecture_firm."Customer"("ID");


--
-- Name: Student_Project Student_Project_Project_ID_fkey; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_Project"
    ADD CONSTRAINT "Student_Project_Project_ID_fkey" FOREIGN KEY ("Project_ID") REFERENCES architecture_firm."Project"("Project_ID");


--
-- Name: Student_Project Student_Project_Student_ID_fkey; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_Project"
    ADD CONSTRAINT "Student_Project_Student_ID_fkey" FOREIGN KEY ("Student_ID") REFERENCES architecture_firm."Student_trainee"("ID");


--
-- Name: Student_trainee Student_trainee_Supervisor_ID_fkey; Type: FK CONSTRAINT; Schema: architecture_firm; Owner: ghassanq
--

ALTER TABLE ONLY architecture_firm."Student_trainee"
    ADD CONSTRAINT "Student_trainee_Supervisor_ID_fkey" FOREIGN KEY ("Supervisor_ID") REFERENCES architecture_firm."Full-time_Engineer"("ID");


--
-- PostgreSQL database dump complete
--

