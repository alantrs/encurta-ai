DO
$$
BEGIN
   IF NOT EXISTS (
      SELECT 1
      FROM pg_database
      WHERE datname = 'shorten_it'
   ) THEN
      PERFORM pg_terminate_backend(pid)
      FROM pg_stat_activity
      WHERE datname = 'shorten_it';

EXECUTE 'CREATE DATABASE shorten_it';
END IF;
END
$$;
