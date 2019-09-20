(ns cats.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(s/defschema Cat
  {:name s/Str
   :image-url s/Str})

(def app
  (api
    {:swagger
     {:ui "/"
      :spec "/swagger.json"
      :data {:info {:title "Cats"
                    :description ""}
             :tags [{:name "api", :description "some apis"}]}}}

    (context "/api" []
      :tags ["api"]

      (GET "/cat" []
        :return {:name s/Str
                 :image-location s/Str}
        :query-params []
        :summary ""
        (ok {:name "tibbers"
             :image-location "https://www.thesprucepets.com/thmb/mTXXYS3dw-IQrzOxUZf029P8C78=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ginger-kitten-868495272-5c5b3cafc9e77c0001d31a4f.jpg"}))

      (POST "/echo" []
        :return {:message s/Str}
        :body [cat Cat]
        :summary "Posts a cat"
        (ok {:message (str (:name cat) "has found a new home in the cloud.")})))))
