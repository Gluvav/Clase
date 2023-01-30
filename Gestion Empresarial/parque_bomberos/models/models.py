# -*- coding: utf-8 -*-
from datetime import date

import dateutil.utils
from dateutil.relativedelta import relativedelta

from odoo import models, fields, api


class parque(models.Model):
    _name = 'parque_bomberos.parque'
    _description = 'parque_bomberos.parque'

    id_parque = fields.Char(string='Id Parque')
    fecha_inauguracion = fields.Date(string='Fecha Inauguración')
    nombre_parque = fields.Char(string='Nombre del parque')

    camion_id = fields.One2many('parque_bomberos.camion', 'parque_id', string='Camiones')


class camion(models.Model):
    _name = 'parque_bomberos.camion'
    _description = 'parque_bomberos.camion'

    id_camion = fields.Char(string='ID Camion')
    matricula = fields.Char(string='Matricula')

    parque_id = fields.Many2one('parque_bomberos.parque', string='Parque')
    bombero_id = fields.One2many('parque_bomberos.bombero', 'id_camion', string='Bomberos')

class bombero(models.Model):

    _name = 'parque_bomberos.bombero'
    _description = 'parque_bomberos.bombero'

    id_bombero = fields.Char(string='ID Bombero')
    dni = fields.Char(string='DNI')
    nombre = fields.Char(string='Nombre')
    apellidos = fields.Char(string='Apellidos')
    fecha_nacimiento = fields.Date("Fecha de Nacimiento")

    age = fields.Integer(string='Edad', compute='_age_compute')

    id_camion = fields.Many2one('parque_bomberos.camion', string='Camion')


    def _age_compute(self):
        today = date.today()
        for record in self:
            record.age = relativedelta(today, record.fecha_nacimiento).years

#class parque_bomberos(models.Model):
#     _name = 'parque_bomberos.parque_bomberos'
#     _description = 'parque_bomberos.parque_bomberos'

#     name = fields.Char()
#     value = fields.Integer()
#     value2 = fields.Float(compute="_value_pc", store=True)
#     description = fields.Text()
#
#     @api.depends('value')
#     def _value_pc(self):
#         for record in self:
#             record.value2 = float(record.value) / 100
