getRequestURL host apiKey resource id = host ++
                                        "/" ++
                                        resource ++
                                        "/" ++
                                        id ++
                                        "?token=" ++
                                        apiKey

genHostRequestBuilder host = (\apiKey resource id ->
                               getRequestURL host apiKey resource id)

exampleUrlBuilder = genHostRequestBuilder "http://example.com"

-- GHCi> exampleUrlBuilder "1337hAsk3ll" "book" "1234"


