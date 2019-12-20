getRequestURL host apiKey resource id = host ++
                                        "/" ++
                                        resource ++
                                        "/" ++
                                        id ++
                                        "?token=" ++
                                        apiKey

-- GHCi> getRequestURL "http://example.com" "1337hAsk3ll" "book" "1234"

