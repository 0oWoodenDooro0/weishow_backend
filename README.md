# weishow_backend

# Management [/management]

## Add a Management [POST]

+ Request (application/json)
    + Attributes
        + managerId: int
        + theaterId: int
+ Response 201 (application/json)

# Manager [/manager]

## Add a Manager [POST]

+ Request (application/json)
    + Attributes
        + username: string
        + passsword: string
+ Response 201 (application/json)

## Login [POST]

+ Request (application/json)
    + Attributes
        + username: string
        + passsword: string
+ Response 200 (application/json)

# Member [/member]

## Get all Members [GET]

+ Response 200 (application/json)

## Update a Member [PUT /{id}]

+ Request (application/json)
    + Attributes
        + name: string
        + email: string
        + passsword: string
+ Response 200 (application/json)

## Register [POST]

+ Request (application/json)
    + Attributes
        + name: string
        + email: string
        + passsword: string
+ Response 201 (application/json)

## Login [POST /login]

+ Request (application/json)
    + Attributes
        + email: string
        + passsword: string
+ Response 200 (application/json)

## Delete a Member [DELETE /{id}]

+ Response 200 (application/json)

# Movie [/movie]

## Get all Movie [GET]

+ Response 200 (application/json)

## Add a Movie [POST]

+ Request (application/json)
    + Attributes
        + title: string
        + releaseTime: string (yyyy-mm-dd)
        + durationMin: int
        + description: string
        + thumbnailPath: string
        + contentRatinId: int
+ Response 201 (application/json)

## Update a Movie [PUT /{id}]

+ Request (application/json)
    + Attributes
        + title: string
        + releaseTime: string (yyyy-mm-dd)
        + durationMin: int
        + description: string
        + thumbnailPath: string
        + contentRatinId: int
+ Response 200 (application/json)

## Delete a Movie [DELETE /{id}]

+ Response 200 (application/json)

# Screen [/screen]

## Add a Screen [POST]

+ Request (application/json)
    + Attributes
        + number: int
        + row: int
        + column: int
        + theaterId: int
+ Response 201 (application/json)

# Seat [/seat]

## Add a Seat [POST]

+ Request (application/json)
    + Attributes
        + row: int
        + column: int
        + number: int
+ Response 201 (application/json)

# Session [/session]

## Add a Session [POST]

+ Request (application/json)
    + Attributes
        + price: int
        + screenId: int
        + movieId: int
+ Response 201 (application/json)

## Get Session By TheaterId and MovieId [POST /theater/{id}/movie/{id}]

+ Response 200 (application/json)

# Theater [/theater]

## Get all Theater [GET]

+ Response 200 (application/json)

## Get Theater By ManagerId [GET /manager/{id}]

+ Response 200 (application/json)

## Add a Theater [POST]

+ Request (application/json)
    + Attributes
        + name: string
        + address: string
        + phoneNumber: string
        + description: string
+ Response 201 (application/json)

# Ticket [/ticket]

## Add a Ticket [POST]

+ Request (application/json)
    + Attributes
        + price: int
        + purchaseTime: string ()
        + sessionId: int
        + memberId: int
        + statusId: int
        + seatId: int
        + typeId: int
+ Response 201 (application/json)

## Update a Ticket [POST]

+ Request (application/json)
    + Attributes
        + id: int
        + price: int
        + purchaseTime: string ()
        + sessionId: int
        + memberId: int
        + statusId: int
        + seatId: int
        + typeId: int
+ Response 200 (application/json)

## Get a Tickets By MemberId [GET /member/{memberId}]

+ Request (application/json)
    + Attributes
        + memberId: int
+ Response 200 (application/json)
