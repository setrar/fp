module PhotoGroove exposing (main)

import Html exposing (div, h1, img, text, hr)
import Html.Attributes exposing (..)

view model =
    div [ class "content" ]
        [ h1 [] [ text "Photo Groove" ]
        , div [ id "thumbnails" ]
            [ img [ src "http://elm-in-action.com/1.jpeg" ] []
            , img [ src "http://elm-in-action.com/2.jpeg" ] []
            , img [ src "http://elm-in-action.com/3.jpeg" ] []
            ]
        , hr [] []
        ]


main =
    view "no model yet"

