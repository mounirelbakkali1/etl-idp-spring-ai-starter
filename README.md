## ETEL/IDP simple Implementation using Spring AI

![IDP-101](https://github.com/user-attachments/assets/ba389ce7-b93b-4e53-8780-75698d1e7361)

This project demonstrates a basic ETEL (Extract, Transform, Embed, and Load) pipeline using Spring AI. It showcases the integration of AI capabilities with document processing and Neo4j for knowledge graph storage.

ETL reference:
![etl-class-diagram](https://github.com/user-attachments/assets/baf3d7ba-fa93-467e-9c83-30162fe62cda)

Embedding APIs reference:
![embeddings-api](https://github.com/user-attachments/assets/98c92be7-34be-43a2-a06e-1b3ba7e119c1)



Dependencies
- Spring AI Transformers Starter: For embedding text using transformer models.
- Spring AI Neo4j Store Starter: To store embeddings and relationships in a Neo4j graph database.
- Spring AI OpenAI Starter: For using OpenAI models.
- Spring AI PDF and Tika Document Reader: To extract content from PDF and other document formats.
- AWS S3 Integration: For storing and reading documents from S3.


### features:

- Public REST API for processing files of types: PDF and text
- Document Transformers: KeywordMetadataEnricher, TokenTextSplitter, SummaryMetadataEnricher
- Embedding Creation: Generate embeddings using transformer models or OpenAI's embedding API.
- Data Storage: Store embeddings in Neo4j
- S3 Integration: Upload documents copy to an S3 bucket.

### Pre-requisites to run the Project
1. Start the Neo4j database (I've used Neo4j Desktop)
2. Configure Vector database and credentials
3. Create an S3 bucket for document uploads


### Reference:
- https://docs.spring.io/spring-ai/reference/api/etl-pipeline.html
- https://docs.spring.io/spring-ai/reference/api/vectordbs.html
- https://docs.spring.io/spring-ai/reference/api/embeddings/openai-embeddings.html
- https://docs.spring.io/spring-ai/reference/api/embeddings/onnx.html
- https://docs.spring.io/spring-ai/reference/api/chatclient.html
