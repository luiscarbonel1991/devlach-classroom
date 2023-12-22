create table public.app_users (
                                  user_id bigint primary key not null default nextval('app_users_user_id_seq'::regclass),
                                  username character varying(255) not null,
                                  password character varying(255) not null,
                                  email character varying(255) not null,
                                  enabled boolean not null default true,
                                  created_at timestamp without time zone,
                                  updated_at timestamp without time zone
);
create unique index app_users_username_key on app_users using btree (username);
create unique index app_users_email_key on app_users using btree (email);



create table public.classes (
                                class_id bigint primary key not null default nextval('classes_class_id_seq'::regclass),
                                teacher_profile_id bigint not null default nextval('classes_teacher_profile_id_seq'::regclass),
                                student_profile_id bigint not null default nextval('classes_student_profile_id_seq'::regclass),
                                start_time timestamp without time zone,
                                end_time timestamp without time zone,
                                status character varying(50),
                                foreign key (student_profile_id) references public.profiles (profile_id)
                                    match simple on update no action on delete no action,
                                foreign key (teacher_profile_id) references public.profiles (profile_id)
                                    match simple on update no action on delete no action
);

create table public.profiles (
                                 profile_id bigint primary key not null default nextval('profiles_profile_id_seq'::regclass),
                                 user_id bigint not null default nextval('profiles_user_id_seq'::regclass),
                                 full_name character varying(255),
                                 bio text,
                                 profile_pic_url character varying(255),
                                 profile_type character varying(50),
                                 foreign key (user_id) references public.app_users (user_id)
                                     match simple on update no action on delete no action
);

create table public.regular_availability (
                                             availability_id bigint primary key not null default nextval('regular_availability_availability_id_seq'::regclass),
                                             profile_id bigint not null default nextval('regular_availability_profile_id_seq'::regclass),
                                             day_of_week character varying(50),
                                             start_time time without time zone,
                                             end_time time without time zone,
                                             created_at timestamp(6) without time zone,
                                             deleted_at timestamp(6) without time zone,
                                             updated_at timestamp(6) without time zone,
                                             foreign key (profile_id) references public.profiles (profile_id)
                                                 match simple on update no action on delete no action
);

create table public.reviews (
                                review_id bigint primary key not null default nextval('reviews_review_id_seq'::regclass),
                                class_id bigint not null default nextval('reviews_class_id_seq'::regclass),
                                student_profile_id bigint not null default nextval('reviews_student_profile_id_seq'::regclass),
                                rating integer,
                                comment text,
                                created_at timestamp without time zone,
                                foreign key (class_id) references public.classes (class_id)
                                    match simple on update no action on delete no action,
                                foreign key (student_profile_id) references public.profiles (profile_id)
                                    match simple on update no action on delete no action
);

create table public.weekly_availability (
                                            weekly_id bigint primary key not null default nextval('weekly_availability_weekly_id_seq'::regclass),
                                            profile_id bigint not null default nextval('weekly_availability_profile_id_seq'::regclass),
                                            date date,
                                            start_time time without time zone,
                                            end_time time without time zone,
                                            created_at timestamp(6) without time zone,
                                            deleted_at timestamp(6) without time zone,
                                            updated_at timestamp(6) without time zone,
                                            foreign key (profile_id) references public.profiles (profile_id)
                                                match simple on update no action on delete no action
);

