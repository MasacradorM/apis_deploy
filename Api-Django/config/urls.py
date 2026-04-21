from django.contrib import admin
from django.urls import path, include
from django.http import JsonResponse

def home(request):
    return JsonResponse({'message': 'API Django - Gestión de Productos funcionando'})

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', home),
    path('', include('products.urls')),
]
