(ns clj-blog.frontmatter
  (:require [clj-yaml.core :as yaml]
            [clojure.string :as str]))

(defn- split-lines
  [lines delim]
  (let [x (take-while #(not= delim %) lines)]
    (list x (drop (+ 1 (count x)) lines))))

(defn- parser
  [s]
  (yaml/parse-string s))

(defn- parse-yaml
  [s]
  (yaml/parse-string s))

(defn parse
  [original-body]
  (let [[first-line & rest-lines] (str/split-lines original-body)
        [frontmatter body]        (split-lines rest-lines first-line)]
    (assoc (parse-yaml (str/join "\n" frontmatter)) :body (str/join "\n" body))))
