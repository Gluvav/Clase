# -*- coding: utf-8 -*-
from odoo import models, fields, api, exceptions


class Car(models.Model):
    _name = 'odoo_model_advanced.car'
    _description = 'Coche'

    name = fields.Char(string='Modelo')
    cv = fields.Float(string='CV')
    fuel_litres = fields.Float(string='Litros')
    coche_nuevo = fields.Boolean(string='Coche nuevo')
    seleccion = fields.Selection([('cocheEmpresa', 'Coche de empresa'), ('cocheParticular', 'Coche particular'),
                                  ('cocheAlquiler', 'Coche de alquiler')])
    text = fields.Char(string='Descripcion')
    imagen = fields.Image(string='Imagen')

    marca_id = fields.Many2one('odoo_model_advanced.marca', string='Marca')


class Marca(models.Model):
    _name = 'odoo_model_advanced.marca'
    _description = 'odoo_model_advanced.marca'

    name = fields.Char(string='Marca')

    modelo = fields.One2many('odoo_model_advanced.car', 'marca_id', string='Modelo')

