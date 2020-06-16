successfulRequest :: Maybe Int
successfulRequest = Just 6

failedRequest :: Maybe Int
failedRequest = Nothing

-- ghci> fmap (+ 1) successfulRequest
-- ghci> (+ 1) <$> successfulRequest
successStr :: Maybe String
successStr = show <$> successfulRequest

-- ghci> fmap (+ 1) failedRequest
-- ghci> (+ 1) <$> failedRequest
failStr :: Maybe String
failStr = show <$> failedRequest
