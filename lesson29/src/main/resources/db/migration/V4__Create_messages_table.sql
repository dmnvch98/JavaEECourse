create table messages (
                          id int8 generated by default as identity,
                          message_date timestamp,
                          message_text text,
                          recipient_user_id int8,
                          sender_user_id int8,
                          primary key (id)
)
