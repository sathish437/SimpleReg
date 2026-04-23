-- Fix database schema issue
-- Drop the existing table and let Hibernate recreate it

USE registration;

DROP TABLE IF EXISTS user_entity;
