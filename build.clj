(ns build
  (:refer-clojure :exclude [test])
  (:require
   [clojure.tools.build.api :as b] ; for b/git-count-revs
   [org.corfield.build :as bb]
   ))

(def lib 'org.scicloj/dataset-io)
; alternatively, use MAJOR.MINOR.COMMITS:
;; (def version (format "6.2.%s" (b/git-count-revs nil)))
(def version "1.0.0")
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def jar-file (format "target/%s-%s.jar" (name lib) version))



(defn test "Run the tests." [opts]
  (-> opts
      (assoc :aliases [:runner :dev :test])
      (bb/run-tests)))

(defn- pom-template [version]
  [[:description "more fileformats for tech.ml.dataset"]
   [:url "https://github.com/scicloj/dataset-io"]
   [:licenses
    [:license
     [:name "Eclipse Public License"]
     [:url "http://www.eclipse.org/legal/epl-v10.html"]]]
   [:developers
    [:developer
     [:name "Carsten Behring"]]]
   [:scm
    [:url "https://github.com/scicloj/dataset-io"]
    [:connection "scm:git:https://github.com/scicloj/dataset-io.git"]
    [:developerConnection "scm:git:https://github.com/scicloj/dataset-io.git"]

    [:tag (str version)]]])


(defn jar [_]
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version version
                :basis basis
                :pom-data (pom-template version)
                :src-dirs ["src"]})
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file jar-file}))

(defn generate-pom [_]
  (b/write-pom {:target "."
                :lib lib
                :version version
                :basis basis
                :pom-data (pom-template version)
                :src-dirs ["src"]}))

(defn ci "Run the CI pipeline of tests (and build the JAR)." [opts]
  (-> opts
      (assoc :lib lib :version version
             :aliases [:runner :dev :test])
      (bb/run-tests)
      (bb/clean)
      (jar)))


(defn install "Install the JAR locally." [opts]
  (-> opts
      (assoc :lib lib :version version)
      (bb/install)))

(defn deploy "Deploy the JAR to Clojars." [opts]
  (-> opts
      (assoc :lib lib :version version)
      (bb/deploy)))

