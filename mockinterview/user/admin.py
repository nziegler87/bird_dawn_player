from django.contrib import admin
from django.contrib.auth.admin import UserAdmin

from .form import CustomUserCreationForm, CustomUserChangeForm
from .models import CustomUser


class CustomUserAdmin(UserAdmin):
    add_form = CustomUserCreationForm
    form = CustomUserChangeForm
    model = CustomUser
    list_display = ('email', 'is_staff', 'is_active', 'user_type')
    list_filter = ('email', 'is_staff', 'is_active', 'user_type')
    fieldsets = (
        (None, {'fields': ('email', 'password', 'first_name', 'last_name',
        'user_type', 'major', 'max_interview_slots',
        'coop_company', 'coop_position',
        'instructor', 'linked_persons')}),
        ('Permissions', {'fields': ('is_staff', 'is_active')}),
    )
    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': ('email', 'password1', 'password2', 'first_name', 'last_name', 'user_type', 'major', 'max_interview_slots', 
            'coop_company', 'coop_position', 'instructor', 'linked_persons', 'is_staff', 'is_active')}
        ),
    )
    search_fields = ('email',)
    ordering = ('email',)


admin.site.register(CustomUser, CustomUserAdmin)