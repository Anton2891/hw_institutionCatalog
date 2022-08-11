insert into public.review (institution_id, rating, review)
values  ((select id from public.institution where id = 1), 5, 'review_1'),
        ((select id from public.institution where id = 2), 2, 'review_2'),
        ((select id from public.institution where id = 3), 10, 'review_3'),
        ((select id from public.institution where id = 4), 8, 'review_4'),
        ((select id from public.institution where id = 5), 7, 'review_5'),
        ((select id from public.institution where id = 6), 9, 'review_6'),
        ((select id from public.institution where id = 7), 1, 'review_7'),
        ((select id from public.institution where id = 8), 5, 'review_8'),
        ((select id from public.institution where id = 9), 6, 'review_9'),
        ((select id from public.institution where id = 10), 3, 'review_10');