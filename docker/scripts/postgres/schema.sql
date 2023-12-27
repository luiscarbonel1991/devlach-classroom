CREATE SEQUENCE app_user_user_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE class_package_class_package_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE course_course_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE course_class_class_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE course_pricing_course_pricing_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE profile_profile_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE regular_availability_availability_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE review_review_id_seq
    INCREMENT BY 1
    START WITH 1;

CREATE SEQUENCE weekly_availability_weekly_id_seq
    INCREMENT BY 1
    START WITH 1;

create table public.app_user
(
    user_id    bigint primary key not null default nextval('app_user_user_id_seq'::regclass),
    email      character varying(255),
    username   character varying(255),
    password   character varying(255),
    enabled    boolean,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone
);

create table public.profile
(
    profile_id      bigint primary key not null default nextval('profile_profile_id_seq'::regclass),
    bio             character varying(255),
    created_at      timestamp(6) without time zone,
    deleted_at      timestamp(6) without time zone,
    full_name       character varying(255),
    profile_pic_url character varying(255),
    profile_type    character varying(255),
    updated_at      timestamp(6) without time zone,
    user_id         bigint,
    foreign key (user_id) references public.app_user (user_id)
        match simple on update no action on delete no action
);


create table public.course
(
    course_id          bigint primary key not null default nextval('course_course_id_seq'::regclass),
    active             boolean            not null,
    category           character varying(255),
    description        character varying(255),
    image              character varying(255),
    language           character varying(255),
    level              character varying(255),
    title              character varying(255),
    video              character varying(255),
    teacher_profile_id bigint,
    created_at         timestamp(6) without time zone,
    updated_at         timestamp(6) without time zone,
    deleted_at         timestamp(6) without time zone,
    foreign key (teacher_profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action
);

create table public.course_pricing
(
    course_pricing_id bigint primary key not null default nextval('course_pricing_course_pricing_id_seq'::regclass),
    price_per_hour    numeric(10, 2),
    price_trial       numeric(10, 2),
    course_id         bigint,
    created_at        timestamp(6) without time zone,
    foreign key (course_id) references public.course (course_id)
        match simple on update no action on delete no action
);

create table public.class_package
(
    class_package_id    bigint primary key not null default nextval('class_package_class_package_id_seq'::regclass),
    duration_in_minutes integer            not null,
    number_of_classes   integer            not null,
    remaining_classes   integer            not null,
    title               character varying(255),
    status              character varying(255),
    is_trial            boolean,
    course_price_id     bigint,
    student_profile_id  bigint,
    created_at          timestamp(6) without time zone,
    updated_at          timestamp(6) without time zone,
    foreign key (course_price_id) references public.course_pricing (course_pricing_id)
        match simple on update no action on delete no action,
    foreign key (student_profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action,
    constraint  number_of_classes_check check (number_of_classes > 0)
);

create table public.course_class
(
    class_id            bigint primary key not null default nextval('course_class_class_id_seq'::regclass),
    created_at          timestamp(6) without time zone,
    curse_name          character varying(255),
    date                date,
    end_time            time(6) without time zone,
    focus               character varying(255),
    last_lesson_summary character varying(255),
    link                character varying(255),
    start_time          time(6) without time zone,
    status              character varying(255),
    updated_at          timestamp(6) without time zone,
    class_package_id    bigint,
    student_profile_id  bigint,
    teacher_profile_id  bigint,
    foreign key (class_package_id) references public.class_package (class_package_id)
        match simple on update no action on delete no action,
    foreign key (teacher_profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action,
    foreign key (student_profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action
);


create table public.regular_availability
(
    availability_id bigint primary key not null default nextval('regular_availability_availability_id_seq'::regclass),
    created_at      timestamp(6) without time zone,
    day_of_week     character varying(255),
    deleted_at      timestamp(6) without time zone,
    end_time        time(6) without time zone,
    start_time      time(6) without time zone,
    updated_at      timestamp(6) without time zone,
    profile_id      bigint,
    foreign key (profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action
);

create table public.review
(
    review_id          bigint primary key not null default nextval('review_review_id_seq'::regclass),
    comment            character varying(255),
    created_at         timestamp(6) without time zone,
    rating             integer            not null,
    updated_at         timestamp(6) without time zone,
    class_id           bigint,
    student_profile_id bigint,
    foreign key (class_id) references public.course_class (class_id)
        match simple on update no action on delete no action,
    foreign key (student_profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action
);

create table public.weekly_availability
(
    weekly_id  bigint primary key not null default nextval('weekly_availability_weekly_id_seq'::regclass),
    created_at timestamp(6) without time zone,
    date       date,
    deleted_at timestamp(6) without time zone,
    end_time   time(6) without time zone,
    start_time time(6) without time zone,
    updated_at timestamp(6) without time zone,
    profile_id bigint,
    foreign key (profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action
);

/*create table public.purchased_class_package
(
    id                 bigint primary key not null default nextval('purchased_class_package_id_seq'::regclass),
    remaining_classes  integer            not null,
    status             character varying(255),
    class_package_id   bigint,
    student_profile_id  bigint,
    foreign key (student_profile_id) references public.profile (profile_id)
        match simple on update no action on delete no action,
    foreign key (class_package_id) references public.class_package (class_package_id)
        match simple on update no action on delete no action
);*/