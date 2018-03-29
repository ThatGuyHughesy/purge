(ns purge.core
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]
            [slingshot.slingshot :refer :all]))

(defn retrieve-user-projects [gitlab-url private-token user-id]
  (try
    (-> (client/get (str gitlab-url "/api/v3/users/" user-id "/projects?per_page=100")
                    {:headers {"PRIVATE-TOKEN" private-token}
                     :socket-timeout 10000
                     :conn-timeout 10000
                     :as :json})
        (get :body))
    (catch Exception e
      (println "Error retrieving projects"))))

(defn delete-project [gitlab-url private-token project-id]
  (try
    (client/delete (str gitlab-url "/api/v3/projects/" project-id)
                   {:headers {"PRIVATE-TOKEN" private-token}
                    :socket-timeout 10000
                    :conn-timeout 10000
                    :as :json})
    (println "Successfully deleted fork")
    (catch Exception e
      (println "Error deleting fork") false)))

(defn delete-projects [gitlab-url private-token project-ids]
  (println "Deleting" (count project-ids) "forks")
  (doall
    (map
      (fn [[project-id project-name]]
        (println "Deleting" project-id "-" project-name)
        (delete-project gitlab-url private-token project-id))
      project-ids)))

(defn filter-forked-projects [projects]
  (filter #(:forked_from_project %) projects))

(defn retrieve-project-info [projects]
  (doall
    (map
      (fn [project]
        [(:id project) (:name_with_namespace project)])
      projects)))

(defn -main [& [gitlab-url private-token user-id]]
  (if (not-any? nil? [gitlab-url private-token user-id])
    (->> (retrieve-user-projects gitlab-url private-token user-id)
         (filter-forked-projects)
         (retrieve-project-info)
         (delete-projects gitlab-url private-token))
    (println "Incorrect arguments given - Should be <GITLAB_URL> <PRIVATE_TOKEN> <USER_ID>")))

(apply -main (rest *command-line-args*))