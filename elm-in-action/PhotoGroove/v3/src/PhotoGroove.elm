module PhotoGroove exposing (main)

import Html exposing (div, h1, img, text, hr)
import Html.Attributes exposing (..)

initialModel =
    { photos =
        [ { url = "1.jpeg" }
        , { url = "2.jpeg" }
        , { url = "3.jpeg" }
        ]
    , selectedUrl = "1.jpeg"
    }

urlPrefix =
    "http://elm-in-action.com/"
 
viewThumbnail thumb =
    img [ src (urlPrefix ++ thumb.url) ] []

view model =
    div [ class "content" ]
        [ h1 [] [ text "Model Photo Groove" ]
        , div [ id "thumbnails" ] (List.map viewThumbnail model)
        , hr [] []
        ]

main =
    view initialModel 

