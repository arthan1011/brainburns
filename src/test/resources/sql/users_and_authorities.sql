INSERT INTO public.users (username, password, enabled) VALUES ('test_user', 'test_password', true);
INSERT INTO public.users (username, password, enabled) VALUES ('test_admin', 'admin_password', true);

INSERT INTO public.authorities (username, authority) VALUES ('test_user', 'USER');
INSERT INTO public.authorities (username, authority) VALUES ('test_admin', 'ADMIN');