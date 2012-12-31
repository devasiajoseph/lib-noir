(ns noir.test-io
  (:use clojure.test noir.io)
  (:import java.net.URL
           java.io.File))

(deftest test-resource-path
  (with-redefs [clojure.java.io/resource (fn [path] (new URL  (str "file:///" path)))]
    (is (= (str File/separator "public" File/separator) (resource-path)))))

(deftest test-get-resource 
  (with-redefs [clojure.java.io/resource (fn [path] (new URL  (str "file:///" path)))]
    (is (= (.replaceAll "/public/css/screen.css" "/" File/separator) 
           (.getPath (get-resource "/css/screen.css"))))))
