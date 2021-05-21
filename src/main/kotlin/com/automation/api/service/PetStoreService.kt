package com.automation.api.service

import io.swagger.petstore.SwaggerPetstoreClient
import org.springframework.stereotype.Service

@Service
class PetStoreService : SwaggerPetstoreClient()