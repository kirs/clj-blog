(ns clj-blog.posts
  (:require [markdown.core :as md]
            [clj-time.format :as f]
            [clojure.string :as str]
            [clj-blog.frontmatter :as fm]))

(def directory (clojure.java.io/file "posts/"))
(def files (file-seq directory))
(def posts-files (filter #(.isFile %) files))

(defn prepare-body
  "For backward capability with Jekyll highlight tags"
  [body]
  (str/replace
    (str/replace body #"\{%(\s+)highlight(\s+)(\w+)*(\s)*%\}" "```$3")
    "{% endhighlight %}" "```"))

(defn parse-body
  [body]
  (md/md-to-html-string (prepare-body body)))

(defn parse-post
  [content]
  (let [post-data (fm/parse content)]
    (assoc post-data :body (parse-body (:body post-data)))))

; replace with basename
(defn delete-last-str
  [s token]
  (subs s 0 (str/index-of s token)))

(defn basename
  [path]
  (str/replace path "posts/" ""))

(defn parse-post-filename
  [filename]
  ; borrowed from Jekyll sources: link
  (let [[_ date permalink ext] (re-matches #"^(\d+-\d+-\d+)-(.*)(\.[^.]+)$" filename)]
    { :date date :permalink permalink :ext ext }))

(defn build-permalink
  [path]
  (let [data (parse-post-filename (basename path))]
    (str (str/replace (:date data) "-" "/") "/" (:permalink data))))

(defn open-post
  [path]
  (assoc (parse-post (slurp path)) :path path :permalink (build-permalink (str path))))

(defn all
  []
  (reverse (sort-by :date (map open-post posts-files))))

(defn all-published
  []
  (filter (fn [x]
            (contains? x :published))
          (all)))

(defn get-post-file
  [year month day post]
  (str "posts/" (str/join "-" [year month day post]) ".md"))
