# weishow_backend

# Member [/member]

## Add a Member [POST]

+ Request (application/json)
    + Attributes
        + name: string
        + email: string
        + passsword: string
        + gender: string
+ Response 201 (application/json)

# Movie [/movie]

## Get all movie [GET]

+ Response 200 (application/json)

## Add a movie [POST]

+ Request (application/json)
    + Attributes
        + title: string
        + releaseTime: string (yyyy-mm-dd)
        + durationMin: int
        + description: string
        + thumbnailPath: string
        + contentRatinId: int
+ Response 201

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

# Theater [/theater]

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
