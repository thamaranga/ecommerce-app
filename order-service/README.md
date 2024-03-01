Assume that our discovery server is stopped since some issue.
But still Order service can communicate with inventory service since
order service has its own cache  regarding inventory service endpoits which
was maintained by the discovery server.
This cache is there because  very first time when order service get the inventory service
deails from the discovery server , by default order service is cached it. 